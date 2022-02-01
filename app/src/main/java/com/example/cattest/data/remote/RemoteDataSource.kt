package com.example.cattest.data.remote

class RemoteDataSource(private val api: CatsApi) {
    fun fetchCats() = api.fetchCats()
}