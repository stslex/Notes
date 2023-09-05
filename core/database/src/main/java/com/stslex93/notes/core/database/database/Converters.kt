package com.stslex93.notes.core.database.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun jsonListString(
        value: Set<String>?
    ): String = Gson().toJson(value ?: emptySet<String>())

    @TypeConverter
    fun jsonToListString(
        value: String
    ): Set<String> = if (value.isBlank()) {
        emptySet()
    } else {
        Gson().fromJson(value, Array<String>::class.java).toSet()
    }
}