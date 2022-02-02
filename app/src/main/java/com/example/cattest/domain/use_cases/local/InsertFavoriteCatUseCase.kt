package com.example.cattest.domain.use_cases.local

import com.example.cattest.domain.model.CatImage
import com.example.cattest.domain.repository.LocalRepository
import javax.inject.Inject

class InsertFavoriteCatUseCase @Inject constructor(
    private val repository: LocalRepository
) {

    operator fun invoke(catImage: CatImage) = repository.insertCat(catImage)

}