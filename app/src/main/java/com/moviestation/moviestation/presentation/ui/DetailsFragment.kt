package com.moviestation.moviestation.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviestation.R
import com.example.moviestation.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    var _binding : FragmentDetailsBinding? = null
    val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.name?.text = args.title
        binding?.vote?.text = args.voteAverage
        binding?.overView?.text = args.overView
        Glide.with(this).load("https://image.tmdb.org/t/p/w154" + args.poster).placeholder(R.drawable.logo).into(binding?.poster !!)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}