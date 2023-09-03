package com.stslex93.notes

import android.content.Context
import androidx.navigation.NavHostController
import com.stslex93.notes.core.database.coreDatabaseModule
import com.stslex93.notes.core.navigation.di.moduleCoreNavigation
import com.stslex93.notes.core.notes.di.coreNotesModule
import com.stslex93.notes.feature.edit.di.featureEditModule
import com.stslex93.notes.feature.home.di.featureHomeModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class AppModuleTest : KoinTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `check koin definitions`() {
        val navController = Mockito.mock(NavHostController::class.java)
        koinApplication {
            modules(
                moduleCoreNavigation(navController),
                coreDatabaseModule,
                coreNotesModule,
                featureEditModule,
                featureHomeModule
            )
            checkModules {
                withInstance<Context>()
            }
        }
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}