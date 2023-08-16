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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestation.databinding.FragmentHomeBinding
import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.data.remote.dto.People
import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.presentation.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<HomeViewModel>()
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.MoviesRecyclerview?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding?.TvRecyclerview?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding?.PeopleRecyclerview?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

       lifecycleScope.launch {
           viewModel.movieList.collect{
            val myAdapter = MainAdapter(navController,"home")
            myAdapter.setList(mapTrendingMovieToTrending(it))
               binding?.MoviesRecyclerview?.adapter=myAdapter
           }
       }

        lifecycleScope.launch {
            viewModel.tvList.collect{
                val myAdapter = MainAdapter(navController,"home")
                myAdapter.setList(mapTrendingTvToTrending(it))
                binding?.TvRecyclerview?.adapter=myAdapter
            }
        }


        lifecycleScope.launch {
            viewModel.peopleList.collect{
                val myAdapter = MainAdapter(navController,"home")
                myAdapter.setList(mapTrendingPeopleToTrending(it))
                binding?.PeopleRecyclerview?.adapter=myAdapter
            }
        }

    }

    private fun mapTrendingMovieToTrending(movieList : List<Movies>) : List<Trending>{
        val newList = mutableListOf<Trending>()
        movieList.forEach{
            newList.add(Trending(it.name,it.poster,it.voteAverage,it.overView))
        }
        return newList
    }

    private fun mapTrendingTvToTrending(tvList : List<Tv>) : List<Trending>{
        val newList = mutableListOf<Trending>()
        tvList.forEach{
            newList.add(Trending(it.name,it.poster,it.voteAverage,it.overView))
        }
        return newList
    }

    private fun mapTrendingPeopleToTrending(peopleList : List<People>) : List<Trending>{
        val newList = mutableListOf<Trending>()
        peopleList.forEach{
            newList.add(Trending(it.name,it.poster))
        }
        return newList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}