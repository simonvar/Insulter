package io.github.simonvar.insulter.api

import com.squareup.moshi.Json

data class Insult(val number: Int,
                  val language: String,
                  val insult: String,
                  val created: String,
                  val shown: Int,
                  @Json(name = "createdby") val createdBy: String,
                  val active: Int,
                  val comment: String)