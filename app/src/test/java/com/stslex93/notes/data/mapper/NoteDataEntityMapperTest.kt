package com.stslex93.notes.data.mapper

import com.stslex93.core.Mapper
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NoteDataEntityMapperTest {

    private lateinit var mappper: Mapper.Data<NoteDataModel, NoteEntity>

    @Before
    fun beforeTestStart() {
        mappper = NoteDataEntityMapper()
    }

    @Test
    fun addition_isCorrect() {
        val expectedNoteEntity = NoteEntity(1, "title", "content", 0)
        val note: NoteDataModel = NoteDataModel.Base(1, "title", "content", 0)
        val actualNoteEntity = mappper.map(note)
        Assert.assertEquals(actualNoteEntity, expectedNoteEntity)
    }
}