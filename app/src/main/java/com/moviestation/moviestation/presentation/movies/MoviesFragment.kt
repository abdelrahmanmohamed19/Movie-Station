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
import com.example.moviestation.databinding.FragmentMoviesBinding
import com.moviestation.moviestation.presentation.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding : FragmentMoviesBinding? =null
    private val binding get()= _binding !!
    private val viewModel by viewModels<MoviesViewModel>()
    private val args by navArgs<MoviesFragmentArgs>()
    private lateinit var navController: NavController
    private val mainAdapter by lazy { MainAdapter(navController,"movie") }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesRecyclerView.adapter = mainAdapter
        viewModel.getMovies(args.id)
        lifecycleScope.launch {
            viewModel.state.collect {
                mainAdapter.submitList(it.moviesList)
                if (it.isLoading) {
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        moviesRecyclerView.visibility = View.INVISIBLE
                    }
                } else {
                    binding.apply {
                        progressBar.visibility = View.INVISIBLE
                        moviesRecyclerView.visibility = View.VISIBLE
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