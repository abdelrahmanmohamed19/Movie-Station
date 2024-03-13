package com.moviestation.moviestation.presentation.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.moviestation.databinding.FragmentTvCategoriesBinding
import com.moviestation.moviestation.presentation.adapter.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowCategoriesFragment : Fragment() {

    private var _binding : FragmentTvCategoriesBinding? = null
    private val binding get()= _binding!!
    private val viewModel by viewModels<TvViewModel>()
    private lateinit var navController: NavController
    private val categoriesAdapter by lazy { CategoriesAdapter(navController,"tv") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvCategoriesBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRecyclerview.adapter = categoriesAdapter
        viewModel.getTvShowCategories()
        lifecycleScope.launch {
            viewModel.state.collect{
                categoriesAdapter.submitList(it.tvShowCategoriesList)
                if (it.isLoading) {
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        tvRecyclerview.visibility = View.INVISIBLE
                    }
                } else {
                    binding.apply {
                        progressBar.visibility = View.INVISIBLE
                        tvRecyclerview.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}