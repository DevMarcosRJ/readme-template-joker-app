package com.marcosmendes.jokerapp.presentation

import com.marcosmendes.jokerapp.data.JokeCallback
import com.marcosmendes.jokerapp.data.JokeDayRemoteDataSource
import com.marcosmendes.jokerapp.model.Joke
import com.marcosmendes.jokerapp.view.JokeDayFragment

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
) : JokeCallback {

    fun findRandom() {
        view.showProgress()
        dataSource.findRandom(this)
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