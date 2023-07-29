package com.example.moviestation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviestation.R
import com.example.moviestation.databinding.TrendingItemBinding
import com.example.moviestation.model.TrendingMovies
import com.example.moviestation.model.TrendingTv

class TrendingTvAdapter: RecyclerView.Adapter<TrendingTvAdapter.viewHolder>() {
    var dataList = emptyList<TrendingTv>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trending_item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.binding.movieName.text = currentItem.TvName
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w154" + currentItem.poster).placeholder(
            R.drawable.ic_launcher_background).into(holder.binding.poster)
    }

    override fun getItemCount()=dataList.size

    fun setData (list: List<TrendingTv>) {
        this.dataList = list
    }

    class viewHolder (viewItem : View) : RecyclerView.ViewHolder(viewItem) {
        val binding = TrendingItemBinding.bind(viewItem)
    }
}
