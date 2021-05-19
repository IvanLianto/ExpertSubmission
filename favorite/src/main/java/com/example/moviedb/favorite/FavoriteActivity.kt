package com.example.moviedb.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviedb.R
import com.example.moviedb.favorite.databinding.ActivityFavoriteBinding
import com.example.moviedb.favorite.di.favoriteModule
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        binding.ivActionBack.setOnClickListener { onBackPressed() }

        initViewPager()
    }

    private fun initViewPager() {
        adapter = FavoriteAdapter(this)
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = adapter.itemCount

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.movie)
                1 -> tab.text = resources.getString(R.string.tvshow)
            }
        }.attach()
    }
}