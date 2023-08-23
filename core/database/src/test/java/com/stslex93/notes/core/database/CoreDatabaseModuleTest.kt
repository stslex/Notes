package com.stslex93.notes.core.database

import android.content.Context
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class CoreDatabaseModuleTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `check koin definitions`() {
        koinApplication {
            modules(coreDatabaseModule)
            checkModules {
                withInstance<Context>()
            }
        }
    }
}