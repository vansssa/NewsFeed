package com.example.newsfeed

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppSharePreference @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        const val NEWSFEED_PREFERENCE = "newsfeed"
        const val COUNTRY_INDEX = "key_country"
        const val CATEGORY_INDEX = "key_category"
        const val COUNTRY_DEFAULT_VALUE = "tw"
        const val CATEGORY_DEFAULT_VALUE = "breaking-news"
    }
    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(NEWSFEED_PREFERENCE, Context.MODE_PRIVATE)
    }

    fun getCategory(): String {
        return preferences.getString(CATEGORY_INDEX, CATEGORY_DEFAULT_VALUE) ?: CATEGORY_DEFAULT_VALUE
    }

    fun setCategory(category: String) {
        preferences.edit().putString(CATEGORY_INDEX, category).apply()
    }

    fun getCountry(): String {
        return preferences.getString(COUNTRY_INDEX, COUNTRY_DEFAULT_VALUE) ?: COUNTRY_DEFAULT_VALUE
    }

    fun setCountry(region: String) {
        preferences.edit().putString(COUNTRY_INDEX, region).apply()
    }
}