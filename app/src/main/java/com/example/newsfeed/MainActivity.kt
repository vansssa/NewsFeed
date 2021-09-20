package com.example.newsfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsfeed.extension.attachMainFragment
import com.example.newsfeed.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            this.attachMainFragment(MainFragment.newInstance())

        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}

