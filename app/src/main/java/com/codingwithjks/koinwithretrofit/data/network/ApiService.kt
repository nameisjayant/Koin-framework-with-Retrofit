package com.codingwithjks.koinwithretrofit.data.network

import com.codingwithjks.koinwithretrofit.data.Bus
import retrofit2.Response
import retrofit2.http.*

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

    @DELETE("bus/{bus_no}/")
    suspend fun delete(
        @Path("bus_no") busNo:String
    ):Response<Unit>
}