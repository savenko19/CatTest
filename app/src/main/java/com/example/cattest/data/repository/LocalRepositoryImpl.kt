package com.example.cattest.data.repository

import com.example.cattest.data.local.CatsDao
import com.example.cattest.data.util.toDatabase
import com.example.cattest.data.util.toUi
import com.example.cattest.domain.model.CatImage
import com.example.cattest.domain.repository.LocalRepository
import io.reactivex.Completable
import io.reactivex.Observable

class LocalRepositoryImpl(
    private val dao: CatsDao
) : LocalRepository {

    override fun insertCat(catImage: CatImage) =
        dao.insertFavoriteCat(catImage.toDatabase())


    override fun getFavoritesCats(): Observable<List<CatImage>> =
        dao.getAllFavoriteCats().map { list ->
            list.map { favoriteCatEntity ->
                favoriteCatEntity.toUi()
            }
        }

    override fun deleteFavoriteById(id: String) =
        dao.deleteCat(id)
}