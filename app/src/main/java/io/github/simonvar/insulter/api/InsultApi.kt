package io.github.simonvar.insulter.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface InsultApi {

    @GET("/generate_insult.php")
    fun generateInsult(@Query("lang") language: String,
                       @Query("type") type: String) : Observable<Insult>

}