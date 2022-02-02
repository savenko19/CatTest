package com.example.cattest.ui.cats.view

import androidx.paging.PagingData
import com.example.cattest.data.remote.response.CatResponse
import com.example.cattest.domain.model.CatImage
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface CatsView : MvpView {

    fun initView()

    fun submitAdapter(cats: PagingData<CatImage>)

    fun showSuccessFavorite()

    fun showFailureFavorite(errorMsg: String)

    fun toggleProgressBar(isVisible: Boolean)

    fun showError(errorMsg: String)
}