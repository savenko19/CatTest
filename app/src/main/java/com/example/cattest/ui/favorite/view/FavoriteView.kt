package com.example.cattest.ui.favorite.view

import com.example.cattest.domain.model.CatImage
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FavoriteView : MvpView {

    fun initView()

    fun setFavoriteList(favorites: List<CatImage>)

    fun showRemoveSuccess()

    fun showRemoveFailure(errorMsg: String)

    fun showEmptyInfo()
}