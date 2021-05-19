package com.example.moviedb.favorite.content.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedb.R
import com.example.moviedb.favorite.FavoriteViewModel
import com.example.moviedb.favorite.databinding.FragmentFavoriteTvShowBinding
import com.example.moviedb.interfaces.DataInterface
import com.example.moviedb.ui.content.adapter.ContentAdapter
import com.example.moviedb.ui.detail.DetailActivity
import com.example.moviedb.utils.TYPE_TVSHOW
import com.example.moviedb.utils.show
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment(), DataInterface {

    private lateinit var binding: FragmentFavoriteTvShowBinding
    private lateinit var adapter: ContentAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            adapter = ContentAdapter()
            adapter.dataInterface = this
            binding.rvFavTvshow.adapter = adapter
            binding.layoutEmpty.tvEmpty.text = getString(R.string.no_favorite_tvshow)
            observerRecyclerView()
        }
    }

    private fun observerRecyclerView() {
        viewModel.getListFavoriteTvshow().observe(viewLifecycleOwner, { list ->
            if (list != null) {
                if (list.isNullOrEmpty()) {
                    emptyState(true)
                } else {
                    emptyState(false)
                    adapter.submitList(list)
                }
            }
        })
    }

    private fun emptyState(flag: Boolean) {
        binding.layoutEmpty.emptyLayoutList.show(flag)
        binding.rvFavTvshow.show(!flag)
    }

    override fun onClicked(view: View, data: com.example.moviedb.core.domain.model.DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, data.dataId)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        startActivity(intent)
    }

}