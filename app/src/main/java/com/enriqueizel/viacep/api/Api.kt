package com.enriqueizel.viacep.api

import com.enriqueizel.viacep.model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
  @GET("ws/{cep}/json/")
  fun getEndereco(@Path("cep") cep: String): Call<Endereco>
}