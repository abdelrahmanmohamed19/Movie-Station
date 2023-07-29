package com.example.moviestation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestation.adapter.TrendingMoviesAdapter
import com.example.moviestation.adapter.TrendingPeopleAdapter
import com.example.moviestation.adapter.TrendingTvAdapter
import com.example.moviestation.databinding.FragmentHomeBinding
import com.example.moviestation.viewModel.TrendingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<TrendingViewModel>()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding
    private val movieAdapter = TrendingMoviesAdapter()
    private val tvAdapter = TrendingTvAdapter()
    private val peopleAdapter = TrendingPeopleAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.MoviesRecyclerview?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding?.TvRecyclerview?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding?.PeopleRecyclerview?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

       lifecycleScope.launch {
           viewModel.movieList.collect{
               movieAdapter.setData(it)
               binding?.MoviesRecyclerview?.adapter = movieAdapter
           }
       }

        lifecycleScope.launch {
            viewModel.tvList.collect{
                tvAdapter.setData(it)
                binding?.TvRecyclerview?.adapter = tvAdapter
            }
        }


        lifecycleScope.launch {
            viewModel.peopleList.collect{
                peopleAdapter.setData(it)
                binding?.PeopleRecyclerview?.adapter = peopleAdapter
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}