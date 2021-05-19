package com.example.moviedb.favorite.content.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviedb.favorite.FavoriteViewModel
import com.example.moviedb.favorite.databinding.FragmentFavoriteMovieBinding
import com.example.moviedb.interfaces.DataInterface
import com.example.moviedb.ui.content.adapter.ContentAdapter
import com.example.moviedb.ui.detail.DetailActivity
import com.example.moviedb.utils.TYPE_MOVIE
import com.example.moviedb.utils.show
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment(), DataInterface {
    private lateinit var binding: FragmentFavoriteMovieBinding
    private lateinit var adapter: ContentAdapter
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            adapter = ContentAdapter()
            adapter.dataInterface = this
            binding.rvFavMovie.adapter = adapter
            observerRecyclerView()
        }
    }

    private fun observerRecyclerView() {
        viewModel.getListFavoriteMovie().observe(viewLifecycleOwner, { list ->
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
        binding.rvFavMovie.show(!flag)
    }

    override fun onClicked(view: View, data: com.example.moviedb.core.domain.model.DataModel) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_ID, data.dataId)
        intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        startActivity(intent)
    }

}