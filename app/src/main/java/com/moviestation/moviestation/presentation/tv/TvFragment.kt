package com.moviestation.moviestation.presentation.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestation.databinding.FragmentTvBinding
import com.moviestation.moviestation.presentation.adapter.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvFragment : Fragment() {

    private var _binding : FragmentTvBinding? = null
    val binding get() =_binding
    private val viewModel by viewModels<TvViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTvBinding.inflate(layoutInflater)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myAdapter = CategoriesAdapter(findNavController(),"tv")
        binding?.TvRecyclerview?.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            viewModel.categoriesList.collect{
                myAdapter.setData(it)
            }
        }
        binding?.TvRecyclerview?.adapter = myAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}