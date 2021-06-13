package com.codingwithjks.koinwithretrofit.container

import com.codingwithjks.koinwithretrofit.data.adapter.BusAdapter
import com.codingwithjks.koinwithretrofit.data.repository.MainRepository
import com.codingwithjks.koinwithretrofit.ui.MainViewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class Component : KoinComponent {

    // lazily inject..
    val busAdapter:BusAdapter by inject()
    val mainViewModel:MainViewModel by inject()
    val mainRepository:MainRepository by inject()
}