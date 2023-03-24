package com.marcosmendes.jokerapp.presentation

import com.marcosmendes.jokerapp.data.JokeCallback
import com.marcosmendes.jokerapp.data.JokeRemoteDataSource
import com.marcosmendes.jokerapp.model.Joke
import com.marcosmendes.jokerapp.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
): JokeCallback {

    fun findBy(categoryName: String) {
        view.showProgress()
        dataSource.findBy(categoryName, this)
    }
    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }


}