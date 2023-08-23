package com.stslex93.notes.core.navigation

import android.content.Context
import androidx.navigation.NavHostController
import com.stslex93.notes.core.navigation.di.moduleCoreNavigation
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class ModuleCoreNavigationTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `check koin definitions`() {
        val navController = Mockito.mock(NavHostController::class.java)
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(moduleCoreNavigation(navController))
            checkModules {
                withInstance<Context>()
            }
        }
    }
}