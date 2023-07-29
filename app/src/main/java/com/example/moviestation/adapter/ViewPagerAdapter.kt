package com.example.moviestation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(container : FragmentActivity, private val List : List<Fragment>) : FragmentStateAdapter(container) {
    override fun getItemCount() = List.size

    override fun createFragment(position: Int) = List[position]
}