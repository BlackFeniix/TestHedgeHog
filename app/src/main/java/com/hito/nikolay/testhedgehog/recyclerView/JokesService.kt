package com.hito.nikolay.testhedgehog.recyclerView

import com.hito.nikolay.testhedgehog.model.JokesData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//Retrofit request to get weather information using phone coordinates
interface JokesService {
    @GET("jokes/random/{count}")
    fun getJokesJSON(@Path("count") count: String): Call<JokesData>
}