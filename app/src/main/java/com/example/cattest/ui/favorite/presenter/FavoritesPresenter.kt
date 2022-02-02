package com.example.cattest.ui.favorite.presenter

import android.annotation.SuppressLint
import com.example.cattest.domain.use_cases.local.DeleteFavoriteUseCase
import com.example.cattest.domain.use_cases.local.GetAllFavoriteCatsUseCase
import com.example.cattest.ui.favorite.view.FavoriteView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter @Inject constructor(
    private val getAllFavorites: GetAllFavoriteCatsUseCase,
    private val deleteFavorite: DeleteFavoriteUseCase
) : MvpPresenter<FavoriteView>() {
    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        viewState.initView()
        getAllFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isEmpty()) {
                    viewState.showEmptyInfo()
                } else {
                    viewState.setFavoriteList(it)
                }
            }, {
                viewState.showEmptyInfo()
            })
    }

    @SuppressLint("CheckResult")
    fun deleteCatById(id: String) {
        deleteFavorite(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showRemoveSuccess()
            }, {
                viewState.showRemoveFailure(it.localizedMessage ?: "Failure")
            })
    }
}