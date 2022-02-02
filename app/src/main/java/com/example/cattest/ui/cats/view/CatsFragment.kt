package com.example.cattest.ui.cats.view

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.cattest.CatsApp
import com.example.cattest.R
import com.example.cattest.core.BaseFragment
import com.example.cattest.databinding.FragmentCatsBinding
import com.example.cattest.domain.model.CatImage
import com.example.cattest.ui.cats.adapter.CatPageAdapter
import com.example.cattest.ui.cats.presenter.CatsPresenter
import com.example.cattest.ui.utils.onDownloadImage
import com.example.cattest.ui.utils.toggleVisible
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class CatsFragment : BaseFragment<FragmentCatsBinding>(
    FragmentCatsBinding::inflate
), CatsView {

    @InjectPresenter
    lateinit var presenter: CatsPresenter

    @ProvidePresenter
    fun providePresenter() =
        (requireActivity().application as CatsApp).appComponent.getCatsPresenter()

    private lateinit var pageAdapter: CatPageAdapter

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun initView() = with(binding) {
        pageAdapter = CatPageAdapter({ selectedCat ->
            presenter.onAddFavoriteButton(selectedCat)
        }, { imageUrl ->
            disposable.onDownloadImage(requireContext(), root, imageUrl)
        })
        catsRecycler.adapter = pageAdapter
    }

    override fun submitAdapter(cats: PagingData<CatImage>) {
        lifecycleScope.launch {
            pageAdapter.submitData(cats)
        }
    }

    override fun toggleProgressBar(isVisible: Boolean) = with(binding) {
        progress.toggleVisible(isVisible)
    }

    override fun showError(errorMsg: String) {
        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
    }


    override fun showSuccessFavorite() {
        Snackbar.make(
            binding.root,
            getString(R.string.added_complete),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun showFailureFavorite(errorMsg: String) {
        Snackbar.make(
            binding.root,
            errorMsg,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}