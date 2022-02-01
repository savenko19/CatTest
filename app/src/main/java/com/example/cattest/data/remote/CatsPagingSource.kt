package com.example.cattest.data.remote

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.cattest.data.util.toUi
import com.example.cattest.domain.model.CatImage
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CatsPagingSource(
    private val api: CatsApi
) : RxPagingSource<Int, CatImage>() {

    override fun getRefreshKey(state: PagingState<Int, CatImage>): Int? {
        TODO("Not yet implemented")
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CatImage>> {
        val position = params.key ?: 1

        return api.fetchCatsPage(position, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(it.map {
                    it.toUi()
                }, position)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(data: List<CatImage>, position: Int): LoadResult<Int, CatImage> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = position + 1
        )
    }
}