package com.moviestation.moviestation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviestation.R
import com.example.moviestation.databinding.TrendingItemBinding
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.presentation.home.HomeFragmentDirections
import com.moviestation.moviestation.presentation.movies.MoviesCategorieFragmentDirections
import com.moviestation.moviestation.presentation.search.SearchFragmentDirections
import com.moviestation.moviestation.presentation.tv.TvCategorieFragmentDirections

class MainAdapter (private val navController: NavController, private val name : String) : ListAdapter<Trending, RecyclerView.ViewHolder>(VisitsModelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(TrendingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         when (holder) {
             is MyViewHolder -> holder.bind(getItem(position))
         }
    }

    override fun submitList(list: List<Trending>?) {
        super.submitList(list)
    }

    private inner class MyViewHolder( private val itemBinding : TrendingItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind (item : Trending) {
            itemBinding.movieName.text = item.name
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w154" + item.image).placeholder(R.drawable.logo).into(itemBinding.poster)
            itemView.setOnClickListener {

                when (name) {
                    "home" -> {
                        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                            title = item.name,
                            poster = item.image!!,
                            voteAverage = item.voteAverage.toString(),
                            overView = item.overView
                        )
                        navController.navigate(action)
                    }
                    "search" -> {
                        val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                            title = item.name,
                            poster = item.image!!,
                            voteAverage = item.voteAverage.toString(),
                            overView = item.overView
                        )
                        navController.navigate(action)
                    }
                    "movie" -> {
                        val action =
                            MoviesCategorieFragmentDirections.actionMoviesCategorieFragmentToDetailsFragment(
                                title = item.name,
                                poster = item.image!!,
                                voteAverage = item.voteAverage.toString(),
                                overView = item.overView
                            )
                        navController.navigate(action)
                    }
                    "tv" -> {
                        val action =
                            TvCategorieFragmentDirections.actionTvCategorieFragmentToDetailsFragment(
                                title = item.name,
                                poster = item.image!!,
                                voteAverage = item.voteAverage.toString(),
                                overView = item.overView
                            )
                        navController.navigate(action)
                    }
                }
            }
        }
    }

    private class VisitsModelDiffCallback : DiffUtil.ItemCallback<Trending>() {
        override fun areItemsTheSame(oldItem: Trending, newItem: Trending) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Trending, newItem: Trending) = oldItem == newItem

    }
}
