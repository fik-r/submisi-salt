package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ArticleAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeLiveData()
        viewModel.getArticles()
    }

    private fun initViews() {
        with(binding) {
            refresh.setOnRefreshListener {
                refresh.isRefreshing = false
                viewModel.getArticles()
            }
            adapter = ArticleAdapter {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        it
                    )
                )
            }
            rvList.apply {
                layoutManager =
                    LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                adapter = this@HomeFragment.adapter
            }
        }
    }

    private fun subscribeLiveData() {
        with(viewModel) {
            isLoading.observe(requireActivity()) {
                binding.loading.visibility = if (it) View.VISIBLE else View.GONE
            }
            articles.observe(requireActivity()) {
                adapter.articles = it.toMutableList()
            }
        }
    }
}