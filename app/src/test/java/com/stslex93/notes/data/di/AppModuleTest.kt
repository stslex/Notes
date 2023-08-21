package com.stslex93.notes.data.di

import android.content.Context
import com.stslex93.notes.core.database.coreDatabaseModule
import com.stslex93.notes.core.notes.di.coreNotesModule
import com.stslex93.notes.di.appModule
import com.stslex93.notes.feature.edit.di.featureEditModule
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
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class AppModuleTest : KoinTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun testModule() {
        koinApplication {
            androidContext(Mockito.mock(Context::class.java))
            modules(
                appModule,
                coreDatabaseModule,
                coreNotesModule,
                featureEditModule
            )
            checkModules()
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