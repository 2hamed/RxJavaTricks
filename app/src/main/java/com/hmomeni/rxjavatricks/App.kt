package com.hmomeni.rxjavatricks

import android.app.Application
import com.hmomeni.rxjavatricks.api.Api
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val baseUrl = "https://6c6bb686-a4e5-446e-8638-c19497baf2c7.mock.pstmn.io/"

    val api: Api by lazy {
        Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build().create(Api::class.java)
    }

}