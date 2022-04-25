package com.stslex93.notes.data.mapper

import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.data.model.NoteDataModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NoteEntityDataMapperTest {

    private lateinit var mappper: NoteEntityDataMapper

    @Before
    fun beforeTestStart() {
        mappper = NoteEntityDataMapper.Base()
    }

    @Test
    fun addition_isCorrect() {
        val expectedNoteEntity = NoteDataModel.Base(1, "title", "content", 0)
        val noteModel = NoteEntity(1, "title", "content", 0)
        val actualNoteEntity = mappper.map(noteModel)
        Assert.assertEquals(actualNoteEntity, expectedNoteEntity)
    }
}