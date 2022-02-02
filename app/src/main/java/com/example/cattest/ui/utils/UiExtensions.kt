package com.example.cattest.ui.utils

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager


fun ProgressBar.toggleVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun RecyclerView.setFlexbox() {
    val layoutManager = FlexboxLayoutManager(context)
    layoutManager.flexDirection = FlexDirection.ROW
    layoutManager.alignItems = AlignItems.CENTER

    this.layoutManager = layoutManager

}