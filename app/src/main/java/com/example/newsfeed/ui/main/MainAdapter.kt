package com.example.newsfeed.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.databinding.ItemNewsBinding
import com.example.newsfeed.repository.NewsItems

class MainAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<NewsItemViewHolder>(){

    private lateinit var newsList: List<NewsItems>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item = newsList[position]
        holder.updateNewsList(viewModel, item)
    }

    override fun getItemCount(): Int {
        newsList = viewModel.newsListLiveData.value ?: listOf()
        return newsList.size
    }

}