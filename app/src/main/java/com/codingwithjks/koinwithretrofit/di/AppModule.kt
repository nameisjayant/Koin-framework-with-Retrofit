package com.codingwithjks.koinwithretrofit.di

import com.codingwithjks.koinwithretrofit.data.adapter.BusAdapter
import com.codingwithjks.koinwithretrofit.data.network.providesApiService
import com.codingwithjks.koinwithretrofit.data.network.providesMoshi
import com.codingwithjks.koinwithretrofit.data.network.providesRetrofitBuilder
import com.codingwithjks.koinwithretrofit.data.repository.MainRepository
import com.codingwithjks.koinwithretrofit.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //adapter
    factory { BusAdapter() }

    //retrofit
    single { providesMoshi() }
    single { providesRetrofitBuilder(get()) }
    single { providesApiService(get()) }

    //repository
    factory { MainRepository(get()) }

    //viewmodel
     viewModel { MainViewModel(get()) }
}