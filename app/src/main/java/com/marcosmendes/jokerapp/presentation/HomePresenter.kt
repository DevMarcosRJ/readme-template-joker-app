package com.marcosmendes.jokerapp.presentation

import android.graphics.Color
import android.os.Handler
import android.os.Looper
import com.marcosmendes.jokerapp.data.CategoryRemoteDataSource
import com.marcosmendes.jokerapp.data.ListCategoryCallback
import com.marcosmendes.jokerapp.model.Category
import com.marcosmendes.jokerapp.view.CategoryItem
import com.marcosmendes.jokerapp.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource
    ): ListCategoryCallback {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onSuccess(response: List<String>) {

        val start = 40
        val end = 190
        val diff = (end - start) / response.size

        val categories = response.mapIndexed { index, s ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f
            )
            Category(s, Color.HSVToColor(hsv).toLong())
        }



        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}