package com.example.newsfeed.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.R
import com.example.newsfeed.databinding.MainFragmentBinding
import com.example.newsfeed.repository.HistoryRepository
import com.example.newsfeed.repository.NewsItems
import com.example.newsfeed.ui.dialog.FilterDialog
import com.example.newsfeed.ui.dialog.Tag
import com.example.newsfeed.ui.dialog.allCategory
import com.example.newsfeed.ui.dialog.allProviders
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


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
        _binding = DataBindingUtil.bind(view)
        initUI()
        return _binding!!.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.newsListLiveData.observe(viewLifecycleOwner,
            Observer<List<NewsItems>> {
                adapter.notifyDataSetChanged()
            })

        //mainViewModel.subListLiveData.observe(viewLifecycleOwner,
        //    Observer<List<String>> {
        //        mainViewModel.getAllNewsList(this.filters)
        //    })
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.getTopNewsList()
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
        _binding?.mainList.let {
            it?.layoutManager = LinearLayoutManager(context)
            it?.adapter = adapter
        }
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = _binding?.mainToolbar
        toolbar?.setOnMenuItemClickListener(this)
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