package com.moviestation.moviestation.presentation.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviestation.databinding.FragmentSelectedCategorieBinding
import com.moviestation.moviestation.data.remote.dto.Movies
import com.moviestation.moviestation.presentation.adapter.MainAdapter
import com.moviestation.moviestation.domain.model.Trending
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesCategorieFragment : Fragment() {

    private var _binding : FragmentSelectedCategorieBinding? =null
    val binding get()= _binding !!
    val viewModel by viewModels<MovieViewModel>()
    private val args by navArgs<MoviesCategorieFragmentArgs>()
    private lateinit var navController: NavController
    private val mainAdapter by lazy { MainAdapter(navController,"movie") }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSelectedCategorieBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMoviesCategoriesList(args.ID.toInt())
        lifecycleScope.launch {
            viewModel.movieCategoriesList.collect{
                mainAdapter.submitList(mapMovieToTrending(it))
                binding.RecyclerView.adapter = mainAdapter
            }
        }
    }

    private fun mapMovieToTrending(movieList : List<Movies>) : List<Trending>{
        val newList = mutableListOf<Trending>()
        movieList.forEach{
            newList.add(Trending(it.name,it.poster,it.voteAverage,it.overView))
        }
        return newList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}