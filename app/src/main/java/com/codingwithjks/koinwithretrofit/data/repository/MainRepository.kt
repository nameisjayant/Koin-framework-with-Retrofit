package com.codingwithjks.koinwithretrofit.data.repository

import com.codingwithjks.koinwithretrofit.data.Bus
import com.codingwithjks.koinwithretrofit.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class MainRepository constructor(private val apiService: ApiService) {

    fun getBus(): Flow<List<Bus>> = flow {
        emit(apiService.getBus())
    }.flowOn(Dispatchers.IO)

    fun setBus(bus_no: String, towns: String): Flow<Bus> = flow {
        emit(apiService.setBus(bus_no, towns))
    }.flowOn(Dispatchers.IO)

    fun deleteBus(bus_no: String):Flow<Response<Unit>> = flow {
        emit(apiService.delete(bus_no))
    }.flowOn(Dispatchers.IO)
}