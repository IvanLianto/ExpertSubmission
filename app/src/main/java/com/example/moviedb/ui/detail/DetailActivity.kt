package com.example.moviedb.ui.detail

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviedb.R
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.core.vo.Resource
import com.example.moviedb.databinding.ActivityDetailBinding
import com.example.moviedb.utils.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 0)

        when (intent.getStringExtra(EXTRA_TYPE)) {
            TYPE_MOVIE -> {
                observerDetailMovie(id)
            }
            TYPE_TVSHOW -> {
                observerDetailTvShow(id)
            }
        }
        binding.ivActionBack.setOnClickListener { onBackPressed() }
    }

    private fun observerDetailMovie(movieId: Int) {
        viewModel.getDetailMovieById(movieId).observe(this, { list ->
            if (list != null) {
                when (list) {
                    is Resource.Loading -> isLoading(true)
                    is Resource.Success -> {
                        isLoading(false)
                        list.data?.let {
                            setBinding(it)
                            setIconFavorite(it.favorite)
                        }
                    }
                    is Resource.Error -> {
                        isLoading(false)
                        alertDialog(
                            this,
                            getString(R.string.error),
                            getString(R.string.check_internet)
                        )
                    }
                }
            }
        })

        viewModel.getVideoMovie(movieId).observe(this, { listVideo ->
            if (listVideo != null) {
                when (listVideo) {
                    is Resource.Loading -> isLoading(true)
                    is Resource.Success -> {
                        isLoading(false)
                        listVideo.data?.codeYoutube?.let { loadVideoYoutube(it) }
                    }
                    is Resource.Error -> {
                        isLoading(false)
                        alertDialog(
                            this,
                            getString(R.string.error),
                            getString(R.string.check_internet)
                        )
                    }
                }
            }
        })
    }

    private fun observerDetailTvShow(tvId: Int) {
        viewModel.getDetailTvshowId(tvId).observe(this, { list ->
            if (list != null) {
                when (list) {
                    is Resource.Loading -> isLoading(true)
                    is Resource.Success -> {
                        isLoading(false)
                        list.data?.let {
                            setBinding(it)
                            setIconFavorite(it.favorite)
                        }
                    }
                    is Resource.Error -> {
                        isLoading(false)
                        alertDialog(
                            this,
                            getString(R.string.error),
                            getString(R.string.check_internet)
                        )
                    }
                }
            }
        })

        viewModel.getVideoTvshow(tvId).observe(this, { listVideo ->
            if (listVideo != null) {
                when (listVideo) {
                    is Resource.Loading -> isLoading(true)
                    is Resource.Success -> {
                        isLoading(false)
                        listVideo.data?.codeYoutube?.let { loadVideoYoutube(it) }
                    }
                    is Resource.Error -> {
                        isLoading(false)
                        alertDialog(
                            this,
                            getString(R.string.error),
                            getString(R.string.check_internet)
                        )
                    }
                }
            }
        })
    }

    private fun setBinding(result: DataModel) {
        binding.apply {
            layoutContentDetail.tvTitle.text = result.title
            layoutContentDetail.tvDescription.text = result.description
            layoutContentDetail.tvGenre.text = result.genre
            tvMember.text = result.member
            tvRate.text = result.score
            tvYear.text = result.releaseYear?.split("-")?.get(0)
            Glide.with(this@DetailActivity)
                .load("${LINK_IMAGE_MOVIEDB}${result.imgPoster}")
                .placeholder(R.drawable.ic_loading).error(R.drawable.ic_error).into(ivPoster)
            Glide.with(this@DetailActivity)
                .load("${LINK_IMAGE_MOVIEDB}${result.imgPoster}")
                .placeholder(R.drawable.ic_loading).error(R.drawable.ic_error).into(ivBackCover)
            ivActionShare.setOnClickListener { shareContent(result) }
            ivFavorite.setOnClickListener { setFavorite(result) }
        }
    }

    private fun setFavorite(data: DataModel) {
        if (!data.favorite) {
            favoriteDialog(
                this,
                getString(R.string.favorite),
                String.format(getString(R.string.message_favorite), data.title),
                data,
                viewModel
            )
        } else {
            unFavoriteDialog(
                this,
                getString(R.string.unfavorite),
                String.format(getString(R.string.message_unfavorite), data.title),
                data,
                viewModel
            )
        }

        setIconFavorite(data.favorite)
    }

    private fun setIconFavorite(flag: Boolean) {
        if (flag) {
            binding.ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun loadVideoYoutube(youtubeId: String) {
        lifecycle.addObserver(binding.layoutContentDetail.youtubePlayerView)

        binding.layoutContentDetail.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(youtubeId, 0f)
            }
        })
    }

    private fun shareContent(result: DataModel) {
        val textSend = """
            ${StringBuilder("${resources.getString(R.string.title)} : ${result.title}")}
            ${StringBuilder("${resources.getString(R.string.genre)} : ${result.genre}")}
            ${StringBuilder("${resources.getString(R.string.description)} : ${result.description}")}
            ${
            StringBuilder(
                "${resources.getString(R.string.year)} : ${
                    result.releaseYear?.split("-")?.get(0)
                }"
            )
        }
            ${StringBuilder("${resources.getString(R.string.rate)} : ${result.score}")}
            ${StringBuilder("${resources.getString(R.string.trailer)} : https://www.youtube.com/watch?v=${result.codeYoutube}")}
        """.trimIndent()
        val sendIntent: Intent = Intent().apply {
            flags = FLAG_ACTIVITY_NEW_TASK
            action = ACTION_SEND
            putExtra(EXTRA_TEXT, textSend)
            type = TYPE_SHARE
        }

        val shareIntent = createChooser(sendIntent, null)
        startActivity(shareIntent)

    }

    private fun isLoading(flag: Boolean) {
        binding.apply {
            progressBarDetail.progressBar.show(flag)
            layoutContentDetail.layoutConstraint.show(!flag)
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
}