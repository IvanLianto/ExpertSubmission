package com.example.moviedb.utils

import android.app.AlertDialog
import android.content.Context
import com.example.moviedb.R
import com.example.moviedb.core.domain.model.DataModel
import com.example.moviedb.ui.detail.DetailViewModel
import kotlin.system.exitProcess

fun alertDialog(context: Context, title: String, message: String) {
    val builder = AlertDialog.Builder(context)
    with(builder)
    {
        setTitle(title)
        setMessage(message)
        setPositiveButton(
            context.getString(R.string.ok)
        ) { dialog, _ ->
            dialog.dismiss()
            exitProcess(0)
        }
        setCancelable(false)
        show()
    }
}

fun favoriteDialog(
    context: Context,
    title: String,
    message: String,
    data: DataModel,
    viewModel: DetailViewModel
) {
    val builder = AlertDialog.Builder(context)
    builder.apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(
            context.getString(R.string.ok)
        ) { dialog, _ ->
            viewModel.setFavorite(data)
            dialog.dismiss()
            confirmDialog(
                context,
                context.resources.getString(R.string.success),
                String.format(context.resources.getString(R.string.success_favorite), data.title)
            )
        }
        setNegativeButton(
            context.resources.getString(R.string.no)
        ) { dialog, _ ->
            dialog.dismiss()
        }
        setCancelable(false)
        show()
    }
}

fun unFavoriteDialog(
    context: Context,
    title: String,
    message: String,
    data: DataModel,
    viewModel: DetailViewModel
) {
    val builder = AlertDialog.Builder(context)
    builder.apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(
            context.getString(R.string.ok)
        ) { dialog, _ ->
            viewModel.setFavorite(data)
            dialog.dismiss()
            confirmDialog(
                context,
                context.resources.getString(R.string.success),
                String.format(context.resources.getString(R.string.success_unfavorite), data.title)
            )
        }
        setNegativeButton(
            context.resources.getString(R.string.no)
        ) { dialog, _ ->
            dialog.dismiss()
        }
        setCancelable(false)
        show()
    }
}

fun confirmDialog(context: Context, title: String, message: String) {
    val builder = AlertDialog.Builder(context)
    builder.apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(
            context.getString(R.string.ok)
        ) { dialog, _ ->
            dialog.dismiss()
        }
        setCancelable(false)
        show()
    }
}