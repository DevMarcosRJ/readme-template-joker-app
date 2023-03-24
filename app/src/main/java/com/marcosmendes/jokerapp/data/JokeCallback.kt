package com.marcosmendes.jokerapp.data

import com.marcosmendes.jokerapp.model.Joke

interface JokeCallback {
    fun onSuccess(response: Joke)
    fun onError(response: String)
    fun onComplete()
}