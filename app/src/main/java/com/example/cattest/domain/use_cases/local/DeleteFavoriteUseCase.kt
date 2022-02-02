package com.example.cattest.domain.use_cases.local

import com.example.cattest.domain.repository.LocalRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    operator fun invoke(id: String) = repository.deleteFavoriteById(id)
}