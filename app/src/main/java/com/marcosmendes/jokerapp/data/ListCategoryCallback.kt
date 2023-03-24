package com.marcosmendes.jokerapp.data

interface ListCategoryCallback {
    fun onSuccess(response: List<String>)
    fun onError(response: String)
    fun onComplete()
}