package com.example.cattest.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cattest.R
import com.example.cattest.core.BaseFragment
import com.example.cattest.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        val navController =
            childFragmentManager.findFragmentById(R.id.fragmentContainer)?.findNavController()
        bottomNavigationView.setupWithNavController(navController!!)
    }
}