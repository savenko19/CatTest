package com.example.cattest.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cattest.data.remote.response.CatResponse
import com.example.cattest.domain.model.CatImage
import io.reactivex.Observable
import io.reactivex.Single

interface RemoteRepository {

    fun getCats(): Single<List<CatResponse>>

    fun catObservable(pagingConfig: PagingConfig): Observable<PagingData<CatImage>>
}