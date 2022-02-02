package com.example.cattest.domain.use_cases.local

import com.example.cattest.domain.repository.LocalRepository
import javax.inject.Inject

class GetAllFavoriteCatsUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    operator fun invoke() = repository.getFavoritesCats()
}