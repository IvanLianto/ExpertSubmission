package com.example.moviedb.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviedb.favorite.content.movie.FavoriteMovieFragment
import com.example.moviedb.favorite.content.tvshow.FavoriteTvShowFragment

class FavoriteAdapter(activity: FavoriteActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> FavoriteMovieFragment()
        }
}