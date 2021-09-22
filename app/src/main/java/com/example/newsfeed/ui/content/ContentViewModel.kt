package com.example.newsfeed.ui.content

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import android.webkit.WebView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsfeed.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.webkit.WebViewClient
import android.webkit.WebSettings
import com.example.newsfeed.data.HistoryEntity
import com.example.newsfeed.repository.NewsItems
import java.util.*


@HiltViewModel
class ContentViewModel @Inject constructor(application: Application,
                                           val historyRepository: HistoryRepository) : AndroidViewModel(application) {

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("{loadUrl}")
    fun WebView.loadUrl(url: String) {
        this.settings.javaScriptEnabled = true
        this.webViewClient = WebViewClient()
        this.loadUrl(url)
    }

    fun addHistory(newsItems: NewsItems) {
        historyRepository.addHistory(newsItems)
    }
}