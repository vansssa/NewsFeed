package com.example.newsfeed.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.MainActivity
import com.example.newsfeed.R
import com.example.newsfeed.databinding.MainFragmentBinding
import com.example.newsfeed.extension.launchFragment
import com.example.newsfeed.ui.content.ContentFragment
import com.example.newsfeed.ui.dialog.FilterDialog
import com.example.newsfeed.ui.dialog.Tag
import com.example.newsfeed.ui.dialog.allCategory
import com.example.newsfeed.ui.dialog.allProviders
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(), FilterDialog.OnCategoryFilterCheckedListener,
    Toolbar.OnMenuItemClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    //private val mainViewModel by viewModels<MainViewModel>()
    val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var adapter: MainAdapter

    // Data binding
    private var _binding: MainFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.main_fragment, container, false)
        //_binding = MainFragmentBinding.bind(view) // For ViewBinding
        _binding = DataBindingUtil.bind(view) // For DataBinding
        initUI()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Use viewLifecycleOwner instead of this: https://www.gushiciku.cn/pl/gzt6/zh-tw
        mainViewModel.newsListLiveData.observe(viewLifecycleOwner,
            Observer {
                adapter.notifyDataSetChanged()
            })
        mainViewModel.itemClickEvent.observe(viewLifecycleOwner,
            Observer {
                (activity as MainActivity).launchFragment(this, ContentFragment.newInstance(it))
            })

        mainViewModel.nowPage.observe(viewLifecycleOwner,
        Observer {
            setToolbar(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCategoryFilterChecked(filterTags: List<Tag>) {
        mainViewModel.updateCategoryPreference(filterTags)
    }

    override fun onCountryChecked(country: List<Tag>) {
        mainViewModel.updateCountryPreference(country)
    }

    private fun initUI() {
        adapter = MainAdapter(mainViewModel)
        binding.mainList.layoutManager = LinearLayoutManager(context)
        binding.mainList.adapter = adapter
        binding.sidebar.setOnClickListener {
            (activity as? MainActivity)?.drawerOpenOrClose()
        }
        setToolbar(0)
    }

    private fun setToolbar(nowPage: Int) {
        when (nowPage) {
            1 -> binding.mainToolbar.menu.forEach { it.isVisible = false }
            else -> {
                binding.mainToolbar.menu.forEach { it.isVisible = true }
                binding.mainToolbar.setOnMenuItemClickListener(this)
            }
        }
    }

    private fun launchCategoryFilterDialog() {
        activity?.let {
            FilterDialog.newInstance(getString(R.string.tag_category), allCategory, mainViewModel.getSelectedCategory(), this)
                .show(it.getSupportFragmentManager(), getString(R.string.tag_category))
        }
    }

    private fun launchCountryFilterDialog() {
        activity?.let {
            FilterDialog.newInstance(getString(R.string.tag_country), allProviders, mainViewModel.getSelectedCountry() , this)
                .show(it.getSupportFragmentManager(), getString(R.string.tag_country))
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.filter -> {
                launchCategoryFilterDialog()
                return true
            }
            R.id.subscribe -> {
                launchCountryFilterDialog()
                return true
            }
        }
        return false
    }

}