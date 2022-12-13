package com.e.rickmortyquery

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET
    suspend fun getCharacterById(value:Array<String>):Response<Personaje>
}