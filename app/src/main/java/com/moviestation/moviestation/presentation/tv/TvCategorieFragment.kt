package com.moviestation.moviestation.presentation.tv

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
import androidx.navigation.fragment.navArgs
import com.example.moviestation.databinding.FragmentTvCategorieBinding
import com.moviestation.moviestation.data.remote.dto.Tv
import com.moviestation.moviestation.presentation.adapter.MainAdapter
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.presentation.movies.MoviesCategorieFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvCategorieFragment : Fragment() {

    private var _binding : FragmentTvCategorieBinding? =null
    val binding get()= _binding
    val viewModel by viewModels<TvViewModel>()
    private val args by navArgs<MoviesCategorieFragmentArgs>()
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTvCategorieBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTvCategoriesList(args.ID.toInt())
        lifecycleScope.launch {
            viewModel.tvCategoriesList.collect{
                val myAdapter = MainAdapter(navController,"tv" )
               val list = mapTvToTrending(it)
                myAdapter.setList(list)
                Log.i("TVTV",list.toString())
                binding?.tvRecyclerView?.adapter = myAdapter
            }
        }
    }

    private fun mapTvToTrending(tvList : List<Tv>) : List<Trending>{
        val newList = mutableListOf<Trending>()
        tvList.forEach{
            newList.add(Trending(it.name,it.poster,it.voteAverage,it.overView))
        }
        return newList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}