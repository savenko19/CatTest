package com.example.cattest.ui.cats.presenter

import android.annotation.SuppressLint
import com.example.cattest.domain.model.CatImage
import com.example.cattest.domain.use_cases.FetchCatsPagingUseCase
import com.example.cattest.domain.use_cases.local.InsertFavoriteCatUseCase
import com.example.cattest.ui.cats.view.CatsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class CatsPresenter @Inject constructor(
    private val fetchCatsPaging: FetchCatsPagingUseCase,
    private val insertFavorite: InsertFavoriteCatUseCase
) : MvpPresenter<CatsView>() {

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView()

        fetchCatsPaging()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.toggleProgressBar(false)
                viewState.submitAdapter(it)
            }, { throwable ->
                viewState.showError(throwable.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    fun onAddFavoriteButton(catImage: CatImage) {
        insertFavorite(catImage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showSuccessFavorite()
            }, {
                viewState.showFailureFavorite(it.localizedMessage ?: "Failure")
            })

    }
}