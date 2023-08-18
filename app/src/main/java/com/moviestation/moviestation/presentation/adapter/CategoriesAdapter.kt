package com.moviestation.moviestation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestation.databinding.CategorieItemBinding
import com.moviestation.moviestation.data.remote.dto.Categories
import com.moviestation.moviestation.presentation.movies.MoviesFragmentDirections
import com.moviestation.moviestation.presentation.tv.TvFragmentDirections

class CategoriesAdapter(private val navController: NavController , private val check : String): ListAdapter<Categories,RecyclerView.ViewHolder>(VisitsModelDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(CategorieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun submitList(list: List<Categories>?) {
        super.submitList(list)
    }

    private class VisitsModelDiffCallback : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Categories, newItem: Categories) = oldItem == newItem

    }

    private inner class MyViewHolder(val itemBinding: CategorieItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind (item : Categories) {
            itemBinding.Id.text = item.id.toString()
            itemBinding.Name.text = item.name
            itemView.setOnClickListener {
                when (check) {
                    "movies" -> {
                        val action =
                            MoviesFragmentDirections.actionMoviesFragmentToMoviesCategorieFragment(
                                item.id.toString()
                            )
                        navController.navigate(action)
                    }

                    "tv" -> {
                        val action =
                            TvFragmentDirections.actionTvFragmentToTvCategorieFragment(item.id.toString())
                        navController.navigate(action)
                    }
                }
            }
        }
    }
}