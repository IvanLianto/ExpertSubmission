package com.example.moviedb.interfaces

import android.view.View
import com.example.moviedb.core.domain.model.DataModel

interface DataInterface {
    fun onClicked(view: View, data: DataModel)
}