package com.codingwithjks.koinwithretrofit.data.network

import com.codingwithjks.koinwithretrofit.data.Bus
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object {
        const val BASE_URL = "https://dtc-api.herokuapp.com/"
    }

    @GET("bus")
    suspend fun getBus(): List<Bus>

    @POST("bus/")
    @FormUrlEncoded
    suspend fun setBus(
        @Field("bus_no") bus_no: String,
        @Field("towns") towns: String
    ): Bus
}