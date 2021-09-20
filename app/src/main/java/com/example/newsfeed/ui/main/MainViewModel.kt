package com.example.newsfeed.ui.main

import android.app.Application
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsfeed.AppSharePreference
import com.example.newsfeed.repository.NewsItems
import com.example.newsfeed.repository.TopNewsRepository
import com.example.newsfeed.ui.dialog.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val topNewsRepository: TopNewsRepository,
                                        application: Application,
                                        private val appSharePreference: AppSharePreference): AndroidViewModel(application) {

    var newsListLiveData : MutableLiveData<List<NewsItems>> = MutableLiveData()
    var itemClickEvent: MutableLiveData<NewsItems> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    init {
        getTopNewsList()
    }

    fun getTopNewsList() {
        val resultList = mutableListOf<NewsItems>()
        compositeDisposable.add(
        topNewsRepository.getTopNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                if (it.isSuccessful) {
                    it.body()?.let {
                        it.articles.forEach { artist ->
                            resultList.add(
                                NewsItems(
                                    artist.title.hashCode().toString(),
                                    artist.title, artist.description, artist.content,
                                    artist.source.fromName, artist.published, artist.url, artist.source.fromName
                                )
                            )
                        }
                    }
                }
            }
            .subscribe { result ->
                newsListLiveData.postValue(resultList)
            }
        )
    }

    fun updateCountryPreference(country: List<Tag>) {
        country.forEach {
           appSharePreference.setCountry(it.name)
       }
        getTopNewsList()
    }

    fun updateCategoryPreference(category: List<Tag>) {
        category.forEach {
            appSharePreference.setCategory(it.name)
        }
        getTopNewsList()
    }

    fun getSelectedCategory(): List<Tag> {
        allCategory.forEach {
            if (it.name == appSharePreference.getCategory()) {
                return listOf(Tag(it.id, it.name))
            }
        }
        return listOf(Tag(FilterType.HEALTH.ordinal, FilterType.HEALTH.name))
    }

    fun getSelectedCountry(): List<Tag> {
        allProviders.forEach {
            if (it.name == appSharePreference.getCountry()) {
                return listOf(Tag(it.id, it.name))
            }
        }
        return listOf(Tag(CountryType.JAPAN.ordinal, CountryType.JAPAN.name))
    }

    fun goToNewsDetail(newsItems: NewsItems) {
        itemClickEvent.postValue(newsItems)
    }

}