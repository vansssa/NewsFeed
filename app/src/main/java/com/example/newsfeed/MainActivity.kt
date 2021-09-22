package com.example.newsfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.newsfeed.databinding.MainActivityBinding
import com.example.newsfeed.extension.attachMainFragment
import com.example.newsfeed.extension.isFragmentIsVisible
import com.example.newsfeed.ui.content.ContentFragment
import com.example.newsfeed.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    val mainFragment = MainFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            this.attachMainFragment(mainFragment)
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_history -> {
                    drawerOpenOrClose()
                    if (!this.isFragmentIsVisible(mainFragment.javaClass.simpleName)) {
                        supportFragmentManager.popBackStack()
                    }
                    mainFragment.mainViewModel.getHistoryRecords()
                    mainFragment.mainViewModel.updateToolbarStyle(1)
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    drawerOpenOrClose()
                    if (!this.isFragmentIsVisible(mainFragment.javaClass.simpleName)) {
                        supportFragmentManager.popBackStack()
                    }

                    mainFragment.mainViewModel.getTopNewsList()
                    mainFragment.mainViewModel.updateToolbarStyle(0)
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun drawerOpenOrClose() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        } else {
            binding.drawer.openDrawer(GravityCompat.START)
        }
    }
}

