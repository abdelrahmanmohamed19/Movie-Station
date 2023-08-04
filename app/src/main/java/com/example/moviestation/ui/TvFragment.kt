package com.example.moviestation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestation.adapter.TvCategoriesAdapter
import com.example.moviestation.databinding.FragmentTvBinding
import com.example.moviestation.viewModel.TvViewModel
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
        val tvAdapter = TvCategoriesAdapter()
        binding?.TvRecyclerview?.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            viewModel.CategorieList.collect{
                tvAdapter.setData(it)
            }
        }
        binding?.TvRecyclerview?.adapter = tvAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}