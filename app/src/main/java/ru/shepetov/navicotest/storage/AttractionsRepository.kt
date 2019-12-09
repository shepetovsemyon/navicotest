package ru.shepetov.navicotest.storage

import ru.shepetov.navicotest.data.Attraction

interface AttractionsRepository {
    suspend fun fetchAttractionsList(): List<Attraction>
}