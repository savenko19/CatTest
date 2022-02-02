package com.example.cattest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.example.cattest.data.remote.CatsApi
import com.example.cattest.data.remote.CatsPagingSource
import com.example.cattest.data.remote.RemoteDataSource
import com.example.cattest.data.remote.response.CatResponse
import com.example.cattest.data.remote.response.Response
import com.example.cattest.domain.model.CatImage
import com.example.cattest.domain.repository.RemoteRepository
import io.reactivex.Observable
import io.reactivex.Single

class RemoteRepositoryImpl(
    private val dataSource: RemoteDataSource,
    private val api: CatsApi
) : RemoteRepository {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20

        //get doggo repository instance
    }

    override fun getCats() = dataSource.fetchCats()

    override fun catObservable(pagingConfig: PagingConfig): Observable<PagingData<CatImage>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CatsPagingSource(api) }
        ).observable
    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }
}