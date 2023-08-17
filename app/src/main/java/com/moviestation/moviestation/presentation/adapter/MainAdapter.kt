package com.moviestation.moviestation.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviestation.R
import com.example.moviestation.databinding.TrendingItemBinding
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.presentation.home.HomeFragmentDirections
import com.moviestation.moviestation.presentation.movies.MoviesCategorieFragmentDirections
import com.moviestation.moviestation.presentation.search.SearchFragmentDirections
import com.moviestation.moviestation.presentation.tv.TvCategorieFragmentDirections

class MainAdapter (val navController: NavController ,val name : String) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    private var myHomeDataList = emptyList<Trending>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.trending_item,parent,false)
       return MyViewHolder(view)
    }

    override fun getItemCount() = myHomeDataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myHomeDataList[position]
        holder.binding.movieName.text = currentItem.name
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w154" + currentItem.image).placeholder(R.drawable.logo).into(holder.binding.poster)
        holder.itemView.setOnClickListener {
            if (name == "home") {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(title = currentItem.name, poster = currentItem.image !! , voteAverage = currentItem.voteAverage.toString() , overView = currentItem.overView)
                navController.navigate(action)
            }
            else if(name == "search")
            {
                val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(title = currentItem.name, poster = currentItem.image !! , voteAverage = currentItem.voteAverage.toString() , overView = currentItem.overView)
                navController.navigate(action)
            }
            else if (name == "movie"){
                val action = MoviesCategorieFragmentDirections.actionMoviesCategorieFragmentToDetailsFragment(title = currentItem.name, poster = currentItem.image !! , voteAverage = currentItem.voteAverage.toString() , overView = currentItem.overView)
                navController.navigate(action)
            }
            else if (name == "tv"){
                val action = TvCategorieFragmentDirections.actionTvCategorieFragmentToDetailsFragment(title = currentItem.name, poster = currentItem.image !! , voteAverage = currentItem.voteAverage.toString() , overView = currentItem.overView)
                navController.navigate(action)
            }
        }

    }

    fun setList(list: List<Trending>) {
        this.myHomeDataList = list
        notifyDataSetChanged()
    }

    class MyViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem){
        val binding = TrendingItemBinding.bind(viewItem)
    }
}