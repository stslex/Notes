package com.stslex93.notes.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NotePagingEntityDataMapperTest {

    private lateinit var mappper: NotePagingEntityDataMapper

    @Before
    fun beforeTestStart() {
        val noteEntityDataMapper = NoteEntityDataMapper.Base()
        mappper = NotePagingEntityDataMapper.Base(noteEntityDataMapper)
    }

    @Test
    fun addition_isCorrect() {
        val expectedPagingData =
            PagingData.from(listOf(NoteDataModel.Base(1, "title", "content", 0)))
        val noteModel = PagingData.from(listOf(NoteEntity(1, "title", "content", 0)))
        val actualPagingData = mappper.map(noteModel)
        expectedPagingData.map { expectedItem ->
            actualPagingData.map { actualItem ->
                Assert.assertEquals(expectedItem, actualItem)
            }
        }
    }
}