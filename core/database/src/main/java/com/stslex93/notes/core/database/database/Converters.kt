package com.stslex93.notes.core.database.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun jsonListString(
        value: List<String>?
    ): String = Gson().toJson(value ?: emptyList<String>())

    @TypeConverter
    fun jsonToListString(
        value: String
    ): List<String> = if (value.isBlank()) {
        emptyList()
    } else {
        Gson().fromJson(value, Array<String>::class.java).toList()
    }
}