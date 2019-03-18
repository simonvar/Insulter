package io.github.simonvar.insulter.api

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class InsultApiService(private val type: String) {

    companion object {
        private const val INSULT_API = "https://evilinsult.com/"
    }

    private val converterFactory by lazy {
        MoshiConverterFactory.create()
    }

    private val callAdapterFactory by lazy {
        RxJava2CallAdapterFactory.create()
    }

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(INSULT_API)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory).build()
    }

    private val insultApi by lazy {
        retrofit.create(InsultApi::class.java)
    }

    fun generateInsult(language: String) = insultApi.generateInsult(language, type)
        .subscribeOn(Schedulers.io())

}