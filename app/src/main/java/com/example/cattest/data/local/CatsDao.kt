package com.example.cattest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cattest.data.local.entity.FavoriteCatEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface CatsDao {

    @Insert
    fun insertFavoriteCat(favoriteCatEntity: FavoriteCatEntity): Completable

    @Query("SELECT * FROM FavoriteCatEntity")
    fun getAllFavoriteCats(): Observable<List<FavoriteCatEntity>>

    @Query("DELETE FROM FavoriteCatEntity WHERE catId =:id")
    fun deleteCat(id: String): Completable
}