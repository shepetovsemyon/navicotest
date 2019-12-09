package ru.shepetov.navicotest.storage

import ru.shepetov.navicotest.data.Attraction
import ru.shepetov.navicotest.network.AttractionsApi

class AttractionsRepositoryImpl(private val attractionsApi: AttractionsApi) : AttractionsRepository {
    override suspend fun fetchAttractionsList(): List<Attraction> = attractionsApi.regions("q")
}

