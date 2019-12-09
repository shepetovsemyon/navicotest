package ru.shepetov.navicotest.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.shepetov.navicotest.data.Attraction

interface AttractionsApi {
    @GET("pois.json")
    suspend fun regions(@Query("name") name: String): List<Attraction>
}