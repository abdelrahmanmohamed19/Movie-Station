package com.moviestation.moviestation.presentation.tv

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
import com.example.moviestation.databinding.FragmentTvShowsBinding
import com.moviestation.moviestation.presentation.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvShowsFragment : Fragment() {

    private var _binding : FragmentTvShowsBinding? = null
    private val binding get() =_binding!!
    private val viewModel by viewModels<TvViewModel>()
    private lateinit var navController: NavController
    private val args by navArgs<TvShowsFragmentArgs>()
    private val mainAdapter by lazy { MainAdapter(navController,"tv") }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvShowsBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRecyclerView.adapter = mainAdapter
        viewModel.getTvShows(args.id)
        lifecycleScope.launch {
            viewModel.state.collect{
                mainAdapter.submitList(it.tvShowsList)
                if (it.isLoading) {
                    binding.apply {
                        progressBar.visibility = View.VISIBLE
                        tvRecyclerView.visibility = View.INVISIBLE
                    }
                } else {
                    binding.apply {
                        progressBar.visibility = View.INVISIBLE
                        tvRecyclerView.visibility = View.VISIBLE
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