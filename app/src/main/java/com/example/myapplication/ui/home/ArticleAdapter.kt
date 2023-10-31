package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.model.Article
import com.example.myapplication.databinding.ItemNewsBinding
import com.example.myapplication.util.DateFormatter

class ArticleAdapter(private val onItemClick: (article: Article) -> Unit) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var articles = mutableListOf<Article>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(
        private val binding: ItemNewsBinding,
        private val onItemClick: (article: Article) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(article: Article) {
            with(binding) {
                tvSource.text = article.source.name
                tvDate.text = DateFormatter.format(article.publishedAt)
                tvTitle.text = article.title
                root.setOnClickListener {
                    onItemClick.invoke(article)
                }
                Glide.with(root)
                    .load(article.urlToImage)
                    .into(thumbnail)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        articles[position].let {
            holder.bindData(it)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }

    override fun getItemCount(): Int = articles.size
}