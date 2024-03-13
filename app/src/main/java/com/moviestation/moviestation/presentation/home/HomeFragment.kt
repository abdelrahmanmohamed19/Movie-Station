package com.moviestation.moviestation.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.moviestation.databinding.FragmentHomeBinding
import com.moviestation.moviestation.presentation.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var navController: NavController
    private val moviesAdapter by lazy { MainAdapter(navController = navController, name = "home") }
    private val tvAdapter by lazy { MainAdapter(navController = navController, name = "home") }
    private val peopleAdapter by lazy { MainAdapter(navController = navController, name = "home") }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.MoviesRecyclerview.adapter = moviesAdapter
        binding.TvRecyclerview.adapter = tvAdapter
        binding.PeopleRecyclerview.adapter = peopleAdapter

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.isLoadingMovies) {
                    binding.apply {
                        progressBar1.visibility = View.VISIBLE
                        MoviesRecyclerview.visibility = View.INVISIBLE
                    }
                } else {
                    moviesAdapter.submitList(state.trendingMoviesList)
                    binding.apply {
                        progressBar1.visibility = View.GONE
                        MoviesRecyclerview.visibility = View.VISIBLE
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.isLoadingTvShows) {
                    binding.apply {
                        progressBar2.visibility = View.VISIBLE
                        TvRecyclerview.visibility = View.INVISIBLE
                    }
                } else {
                    tvAdapter.submitList(state.trendingTvShowsList)
                    binding.apply {
                        progressBar2.visibility = View.GONE
                        TvRecyclerview.visibility = View.VISIBLE
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.isLoadingPeople) {
                    binding.apply {
                        progressBar3.visibility = View.VISIBLE
                        PeopleRecyclerview.visibility = View.INVISIBLE
                    }
                } else {
                    peopleAdapter.submitList(state.trendingPeopleList)
                    binding.apply {
                        progressBar3.visibility = View.GONE
                        PeopleRecyclerview.visibility = View.VISIBLE
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