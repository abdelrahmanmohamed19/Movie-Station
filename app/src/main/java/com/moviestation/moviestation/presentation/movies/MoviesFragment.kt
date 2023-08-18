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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestation.databinding.FragmentMoviesBinding
import com.moviestation.moviestation.presentation.adapter.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding : FragmentMoviesBinding? = null
    val binding get() =_binding !!
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var navController : NavController
    private val categoriesAdapter by lazy { CategoriesAdapter(navController,"movies") }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.MovieRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            viewModel.categoriesList.collect{
                categoriesAdapter.submitList(it)
            }
        }
        binding.MovieRecyclerview.adapter = categoriesAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}