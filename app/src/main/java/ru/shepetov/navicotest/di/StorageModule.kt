package ru.shepetov.navicotest.di

import org.koin.dsl.module
import ru.shepetov.navicotest.storage.AttractionsRepository
import ru.shepetov.navicotest.storage.AttractionsRepositoryImpl

val storageModule = module {
    single<AttractionsRepository> { AttractionsRepositoryImpl(get()) }
}
