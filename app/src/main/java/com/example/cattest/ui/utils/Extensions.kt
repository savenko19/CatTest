package com.example.cattest.ui.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.View
import com.example.cattest.R
import com.google.android.material.snackbar.Snackbar
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit

private val catDownloadHelper by lazy {
    CatDownloadHelper(
        OkHttpClient.Builder().build()
    )
}

fun Disposable.onDownloadImage(context: Context, view: View, imageUrl: String): Disposable {
    val target = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
        "${System.currentTimeMillis()}" +
                imageUrl.substring(
                    imageUrl.length - 4,
                    imageUrl.length
                )
    )

    return catDownloadHelper.download(imageUrl, target)
        .throttleFirst(2, TimeUnit.SECONDS)
        .toFlowable(BackpressureStrategy.LATEST)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({

        }, {
            Log.e("Test", it.localizedMessage ?: "Error")
        }, {
            Snackbar.make(
                context,
                view,
                context.getString(R.string.download_complete),
                Snackbar.LENGTH_SHORT
            ).show()
        })
}