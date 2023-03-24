package com.marcosmendes.jokerapp.data

import com.marcosmendes.jokerapp.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.lang.RuntimeException

class JokeRemoteDataSource {
    fun findBy(categoryName: String, callback: JokeCallback) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findRandom(categoryName)
            .enqueue(object  : Callback<Joke> {
                override fun onResponse(
                    call: Call<Joke>,
                    response: Response<Joke>
                ) {
                    if (response.isSuccessful) {
                        val joke = response.body()
                        callback.onSuccess(joke ?: throw RuntimeException("Joke not found"))
                    } else {
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "unkown error")
                    }
                    callback.onComplete()

                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Internal error")
                    callback.onComplete()
                }
            })

    }
}