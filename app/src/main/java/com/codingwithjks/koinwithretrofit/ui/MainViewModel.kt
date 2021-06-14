package com.codingwithjks.koinwithretrofit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithjks.koinwithretrofit.data.util.ApiState
import com.codingwithjks.koinwithretrofit.data.repository.MainRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val _busApi:MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
     val busApi:StateFlow<ApiState> = _busApi

    fun getBus() = viewModelScope.launch {
        mainRepository.getBus()
            .onStart {
                _busApi.value = ApiState.Loading
            }.catch { e->
                _busApi.value = ApiState.Failure(e)
            }.collect { response->
                _busApi.value = ApiState.Success(response)
            }
    }

    fun setBus(bus_no:String,town:String) = mainRepository.setBus(bus_no,town)

    fun deleteBus(
        busNo:String
    ) = mainRepository.deleteBus(busNo)
}