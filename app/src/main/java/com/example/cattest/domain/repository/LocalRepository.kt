package com.example.cattest.domain.repository

import com.example.cattest.domain.model.CatImage
import io.reactivex.Completable
import io.reactivex.Observable

interface LocalRepository {

    fun insertCat(catImage: CatImage) : Completable

    fun getFavoritesCats(): Observable<List<CatImage>>

    fun deleteFavoriteById(id: String): Completable
}