package com.example.moviestation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestation.R
import com.example.moviestation.databinding.CategorieItemBinding
import com.example.moviestation.model.Categorie
import com.example.moviestation.ui.MoviesFragment
import com.example.moviestation.ui.MoviesFragmentDirections
import com.example.moviestation.ui.TvFragment
import com.example.moviestation.ui.TvFragmentDirections

class TvCategoriesAdapter: RecyclerView.Adapter<TvCategoriesAdapter.viewHolder>() {
    var dataList = emptyList<Categorie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.categorie_item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.binding.Name.text = currentItem.name
        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
                Toast.makeText(context,"${currentItem.id}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun getItemCount()=dataList.size

    fun setData (list: List<Categorie>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    class viewHolder (viewItem : View) : RecyclerView.ViewHolder(viewItem) {
        val binding = CategorieItemBinding.bind(viewItem)
    }
}


