package com.example.danteblessyou

import com.example.danteblessyou.model.FavoriteModel

interface ListDoneListener {
    fun onListDone(favoriteModel: FavoriteModel, position: Int)
}