package com.example.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.util.DateFormatter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentDetailBinding.inflate(LayoutInflater.from(requireActivity()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(binding) {
            btnBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
            Glide.with(root)
                .load(args.article.urlToImage)
                .into(thumbnail)
            tvTitle.text = args.article.title
            tvAuthor.text = args.article.author
            tvPublishedAt.text = DateFormatter.format(args.article.publishedAt)
            tvDescription.text = args.article.content
        }
    }
}