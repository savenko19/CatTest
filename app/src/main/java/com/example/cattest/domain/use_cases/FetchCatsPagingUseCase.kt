package com.example.cattest.domain.use_cases

import androidx.paging.PagingConfig
import com.example.cattest.data.repository.RemoteRepositoryImpl
import com.example.cattest.domain.repository.RemoteRepository
import javax.inject.Inject

class FetchCatsPagingUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    operator fun invoke() = repository.catObservable(getDefaultPageConfig())

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = RemoteRepositoryImpl.DEFAULT_PAGE_SIZE,
            enablePlaceholders = true
        )
    }
}