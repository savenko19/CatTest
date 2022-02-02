package com.example.cattest.di

import com.example.cattest.ui.cats.presenter.CatsPresenter
import com.example.cattest.ui.favorite.presenter.FavoritesPresenter
import dagger.Component
import moxy.presenter.ProvidePresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [LocalModule::class, RemoteModule::class])
interface AppComponent {

    @ProvidePresenter
    fun getCatsPresenter(): CatsPresenter

    @ProvidePresenter
    fun getFavoritesPresenter(): FavoritesPresenter
}