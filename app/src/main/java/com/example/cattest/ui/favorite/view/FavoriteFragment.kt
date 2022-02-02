package com.example.cattest.ui.favorite.view

import android.view.View
import com.example.cattest.CatsApp
import com.example.cattest.core.BaseFragment
import com.example.cattest.databinding.FragmentFavoriteBinding
import com.example.cattest.domain.model.CatImage
import com.example.cattest.ui.favorite.adapter.FavoritesAdapter
import com.example.cattest.ui.favorite.presenter.FavoritesPresenter
import com.google.android.material.snackbar.Snackbar
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(
    FragmentFavoriteBinding::inflate
), FavoriteView {

    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    fun providePresenter() =
        (requireActivity().application as CatsApp).appComponent.getFavoritesPresenter()

    private lateinit var adapter: FavoritesAdapter

    override fun initView() {
        adapter = FavoritesAdapter() {
            presenter.deleteCatById(it)
        }
    }

    override fun setFavoriteList(favorites: List<CatImage>) = with(binding) {
        adapter.catImages = favorites
        favoritesRecycler.adapter = adapter
    }

    override fun showRemoveSuccess() {
        Snackbar.make(
            binding.root,
            "Removed",
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun showRemoveFailure(errorMsg: String) {
        Snackbar.make(
            binding.root,
            errorMsg,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    override fun showEmptyInfo() = with(binding) {
        emptyText.visibility = View.VISIBLE
    }
}