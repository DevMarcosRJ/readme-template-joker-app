package com.marcosmendes.jokerapp.data

import android.os.Handler
import android.os.Looper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {
    fun findAllCategories(callback: ListCategoryCallback) {
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories()
            .enqueue(object  : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        val categories = response.body()
                        callback.onSuccess(categories ?: emptyList())
                    } else {
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "erro desconhecido")
                    }
                    callback.onComplete()

                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onError(t.message ?: "Internal error")
                    callback.onComplete()
                }
            })

    }

}