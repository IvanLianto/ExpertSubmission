package com.example.moviedb.ui.home


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviedb.ui.content.movie.MovieFragment
import com.example.moviedb.ui.content.tvshow.TvshowFragment

class HomeAdapter(activity: HomeActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvshowFragment()
            else -> MovieFragment()
        }
}