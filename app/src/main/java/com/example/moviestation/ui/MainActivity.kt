package com.example.moviestation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.fragment.app.FragmentManager
import com.example.moviestation.R
import com.example.moviestation.adapter.ViewPagerAdapter
import com.example.moviestation.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    val binding get() = _binding
    private val tabTitles = listOf("Home","Movies","Tv")
    private val fragmentList = listOf(HomeFragment(),MoviesFragment(),TvFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViewPager()
        initTabLayout()
    }

    fun initTabLayout() {
        TabLayoutMediator(binding?.MyTabLayout!!,binding?.MyViewPager!!){tab,postion ->
           tab.text =tabTitles [postion]
        }.attach()
    }

    fun initViewPager() {
        val adapter = ViewPagerAdapter(this,fragmentList)
        binding?.MyViewPager?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}