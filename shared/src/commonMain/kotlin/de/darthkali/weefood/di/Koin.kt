package de.darthkali.weefood.di

import org.koin.core.context.startKoin
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            Modules.network,
            Modules.database,
            Modules.interactor
        )
    }



// called by iOS
fun initKoin() = initKoin() {}

expect fun platformModule(): Module




