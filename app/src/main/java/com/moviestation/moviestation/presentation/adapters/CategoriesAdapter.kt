package com.moviestation.moviestation.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestation.R
import com.example.moviestation.databinding.CategorieItemBinding
import com.moviestation.moviestation.data.model.Categories
import com.moviestation.moviestation.presentation.ui.MoviesFragmentDirections
import com.moviestation.moviestation.presentation.ui.TvFragmentDirections

class CategoriesAdapter(val navController: NavController , val check : String): RecyclerView.Adapter<CategoriesAdapter.viewHolder>() {
    var dataList = emptyList<Categories>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categorie_item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.binding.Name.text = currentItem.name
        holder.itemView.setOnClickListener {
            if (check == "movies") {
                val action = MoviesFragmentDirections.actionMoviesFragmentToMoviesCategorieFragment(currentItem.id.toString())
                navController.navigate(action)}
            else if (check == "tv") {
                val action = TvFragmentDirections.actionTvFragmentToTvCategorieFragment(currentItem.id.toString())
                 navController.navigate(action)
            }
        }
    }

    override fun getItemCount()=dataList.size

    fun setData (list: List<Categories>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    class viewHolder (viewItem : View) : RecyclerView.ViewHolder(viewItem) {
        val binding = CategorieItemBinding.bind(viewItem)
    }
}