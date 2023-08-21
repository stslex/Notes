package com.stslex93.notes.feature.edit

import android.content.Context
import com.stslex93.notes.core.notes.repository.NoteRepository
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
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class FeatureEditModuleTest : KoinTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun testModule() {
        koinApplication {
            load<NoteRepository>()
            androidContext(Mockito.mock(Context::class.java))
            modules(featureEditModule)
            checkModules()
        }
    }


    private inline fun <reified T> KoinApplication.load() {
        koin.loadModules(
            listOf(module { single<T> { Mockito.mock(T::class.java) } })
        )
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