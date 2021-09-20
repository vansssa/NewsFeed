package com.example.newsfeed.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.databinding.ItemNewsBinding
import com.example.newsfeed.repository.NewsItems

class NewsItemViewHolder(private val binding: ItemNewsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun updateNewsList(viewModel: MainViewModel, newsItems: NewsItems) {
        binding.mainViewModel = viewModel
        binding.newsItem = newsItems
        binding.executePendingBindings()
    }

}
