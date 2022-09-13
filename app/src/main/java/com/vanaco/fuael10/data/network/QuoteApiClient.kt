package com.vanaco.fuael10.data.network

import com.vanaco.fuael10.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET


interface QuoteApiClient {
    @GET("/.json")
    suspend fun getAllQuotes():Response<List<QuoteModel>>
}