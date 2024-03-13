package com.moviestation.moviestation.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.moviestation.databinding.FragmentSearchBinding
import com.moviestation.moviestation.domain.model.Trending
import com.moviestation.moviestation.presentation.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding !!
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var navController: NavController
    private val mainAdapter by lazy { MainAdapter(navController,"search") }
    val list = listOf(Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""),Trending("","",0.0,""))


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRecyclerView.adapter = mainAdapter
        binding.SearchTextField.addTextChangedListener { text ->
            lifecycleScope.launch {
                delay(500)
                viewModel.getSearchedItem(text.toString())
                viewModel.isLoading.collect{
                    if (it) {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                            searchRecyclerView.visibility = View.INVISIBLE
                        }
                    } else {
                        binding.apply {
                            progressBar.visibility = View.INVISIBLE
                            searchRecyclerView.visibility = View.VISIBLE
                        }
                        viewModel.searchedItemList.collect {list ->
                            mainAdapter.submitList(list)
                        }
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}