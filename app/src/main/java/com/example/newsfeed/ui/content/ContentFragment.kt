package com.example.newsfeed.ui.content

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.newsfeed.MainActivity
import com.example.newsfeed.R
import com.example.newsfeed.databinding.ContentFragmentBinding
import com.example.newsfeed.databinding.MainFragmentBinding
import com.example.newsfeed.repository.NewsItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment(val newsItems: NewsItems) : Fragment() {

    // Data binding
    private var _binding: ContentFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance(newsItems: NewsItems) = ContentFragment(newsItems)
    }

    val contentViewModel: ContentViewModel by lazy {
        ViewModelProvider(this).get(ContentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.content_fragment, container, false)
        _binding = DataBindingUtil.bind(view)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.newsItem = newsItems
        binding.newsItem.apply { contentViewModel.addHistory(newsItems) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initToolbar() {
        binding.mainToolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }
}
