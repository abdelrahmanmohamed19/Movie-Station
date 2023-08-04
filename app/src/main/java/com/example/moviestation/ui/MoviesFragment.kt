package com.example.moviestation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestation.R
import com.example.moviestation.adapter.CategoriesAdapter
import com.example.moviestation.databinding.FragmentHomeBinding
import com.example.moviestation.databinding.FragmentMoviesBinding
import com.example.moviestation.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding : FragmentMoviesBinding? = null
    val binding get() =_binding
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val MyAdapter = CategoriesAdapter()
        binding?.MovieRecyclerview?.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            viewModel.CategorieList.collect{
                MyAdapter.setData(it)
            }
        }
        binding?.MovieRecyclerview?.adapter = MyAdapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}