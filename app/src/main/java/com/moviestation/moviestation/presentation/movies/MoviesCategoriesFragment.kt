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
import com.example.moviestation.databinding.FragmentMoviesCategoriesBinding
import com.moviestation.moviestation.presentation.adapter.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesCategoriesFragment : Fragment() {

    private var _binding : FragmentMoviesCategoriesBinding? = null
    private val binding get() =_binding !!
    private val viewModel by viewModels<MoviesViewModel>()
    private lateinit var navController : NavController
    private val categoriesAdapter by lazy { CategoriesAdapter(navController,"movies") }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesCategoriesBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moviesRecyclerview.adapter = categoriesAdapter
        viewModel.getMoviesCategoriesList()
        lifecycleScope.launch {
            viewModel.state.collect{
                categoriesAdapter.submitList(it.moviesCategoriesList)
                if (it.isLoading) {
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        moviesRecyclerview.visibility = View.INVISIBLE
                    }
                } else {
                    binding.apply {
                        progressBar.visibility = View.INVISIBLE
                        moviesRecyclerview.visibility = View.VISIBLE
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