package com.stslex93.notes.core.database

import android.content.Context
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class CoreDatabaseModuleTest : KoinTest {

    @Test
    fun testModule() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(coreDatabaseModule)
            checkModules()
        }
    }
}