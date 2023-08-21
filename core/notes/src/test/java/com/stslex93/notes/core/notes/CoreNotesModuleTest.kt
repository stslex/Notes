package com.stslex93.notes.core.notes

import android.content.Context
import com.stslex93.notes.core.database.NoteDao
import com.stslex93.notes.core.notes.di.coreNotesModule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito

class CoreNotesModuleTest : KoinTest {

    @Test
    fun testModule() {
        koinApplication {
            load<NoteDao>()
            androidContext(Mockito.mock(Context::class.java))
            modules(coreNotesModule)
            checkModules()
        }
    }

    private inline fun <reified T> KoinApplication.load() {
        koin.loadModules(
            listOf(module { single<T> { Mockito.mock(T::class.java) } })
        )
    }
}