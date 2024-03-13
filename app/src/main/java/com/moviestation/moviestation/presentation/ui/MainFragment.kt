package com.moviestation.moviestation.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.moviestation.R
import com.example.moviestation.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding .inflate(layoutInflater)
        return(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().findNavController(R.id.MainFragmentContainerView).popBackStack()
            }
        })

        binding.searchButton.setOnClickListener {
            requireActivity().findNavController(R.id.MainFragmentContainerView).navigate(R.id.searchFragment)
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> requireActivity().findNavController(R.id.MainFragmentContainerView).navigate(R.id.homeFragment)
                R.id.movies -> requireActivity().findNavController(R.id.MainFragmentContainerView).navigate(R.id.moviesCategoriesFragment2)
                R.id.tv_shows -> requireActivity().findNavController(R.id.MainFragmentContainerView).navigate(R.id.tvCategorieFragment2)
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}