package com.example.moviedb.ui.content.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.vo.Resource
import com.example.moviedb.databinding.FragmentTvshowBinding
import com.example.moviedb.interfaces.DataInterface
import com.example.moviedb.ui.content.adapter.ContentAdapter
import com.example.moviedb.ui.detail.DetailActivity
import com.example.moviedb.utils.TYPE_TVSHOW
import com.example.moviedb.utils.alertDialog
import com.example.moviedb.utils.show
import org.koin.android.viewmodel.ext.android.viewModel

class TvshowFragment : Fragment(), DataInterface {
    private lateinit var binding: FragmentTvshowBinding
    private lateinit var adapter: ContentAdapter
    private val viewModel: TvshowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            adapter = ContentAdapter()
            adapter.dataInterface = this
            binding.rvTvshow.adapter = adapter
            binding.emptyLayout.tvEmpty.text = getString(R.string.not_found_search)
            binding.emptyLayout.ivEmpty.setImageResource(R.drawable.ic_baseline_search_off_24)
            observerRecyclerView()
            searchTvShow()
        }
    }

    private fun searchTvShow() {
        binding.svHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isNotEmpty()) {
                        isLoading(true)
                        observerSearchView(query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
        binding.svHome.setOnCloseListener {
            isEmpty(false)
            observerRecyclerView()
            true
        }
    }

    private fun observerSearchView(title: String) {
        viewModel.searchTvShow(title).observe(viewLifecycleOwner, { list ->
            if (list != null) {
                if (list.isNotEmpty()) {
                    isLoading(false)
                    isEmpty(false)
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                } else {
                    isLoading(false)
                    isEmpty(true)
                }
            }
        })
    }

    private fun observerRecyclerView() {
        viewModel.getTvshowsData().observe(viewLifecycleOwner, { list ->
            if (list != null) {
                when (list) {
                    is Resource.Loading -> isLoading(true)
                    is Resource.Success -> {
                        isLoading(false)
                        adapter.submitList(list.data)
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        isLoading(false)
                        alertDialog(
                            requireContext(),
                            getString(R.string.error),
                            getString(R.string.check_internet)
                        )
                    }
                }
            }
        })
    }

    private fun isLoading(flag: Boolean) {
        binding.progressBarTvshow.progressBar.show(flag)
        binding.rvTvshow.show(!flag)
    }

    private fun isEmpty(flag: Boolean) {
        binding.emptyLayout.emptyLayout.show(flag)
        binding.rvTvshow.show(!flag)
    }

    override fun onClicked(view: View, data: DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, data.dataId)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        startActivity(intent)
    }
}