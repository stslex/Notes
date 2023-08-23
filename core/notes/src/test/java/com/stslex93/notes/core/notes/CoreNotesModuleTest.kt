package com.stslex93.notes.core.notes

import android.content.Context
import com.stslex93.notes.core.database.NoteDao
import com.stslex93.notes.core.notes.di.coreNotesModule
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class CoreNotesModuleTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun `check koin definitions`() {
        koinApplication {
            modules(coreNotesModule)
            checkModules {
                withInstance<NoteDao>()
                withInstance<Context>()
            }
        }
    }
}