package com.codingwithjks.koinwithretrofit.di

import com.codingwithjks.koinwithretrofit.MainActivity
import com.codingwithjks.koinwithretrofit.data.adapter.BusAdapter
import com.codingwithjks.koinwithretrofit.data.network.providesApiService
import com.codingwithjks.koinwithretrofit.data.network.providesMoshi
import com.codingwithjks.koinwithretrofit.data.network.providesRetrofitBuilder
import com.codingwithjks.koinwithretrofit.data.repository.MainRepository
import com.codingwithjks.koinwithretrofit.data.util.Listener
import com.codingwithjks.koinwithretrofit.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.bind
import org.koin.dsl.module

@OptIn(KoinApiExtension::class)
val appModule = module {

    factory { MainActivity() } bind Listener::class

    //adapter
    factory { BusAdapter(get()) }

    //retrofit
    single { providesMoshi() }
    single { providesRetrofitBuilder(get()) }
    single { providesApiService(get()) }

    //repository
    factory { MainRepository(get()) }

    //viewmodel
     viewModel { MainViewModel(get()) }
}