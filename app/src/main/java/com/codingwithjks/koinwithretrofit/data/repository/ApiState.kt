package com.codingwithjks.koinwithretrofit.data.repository

import com.codingwithjks.koinwithretrofit.data.Bus

sealed class ApiState{

    class Success(val data:List<Bus>) : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}
