package com.stslex93.notes.data.database.base

interface BaseRoomDatabase<T> {
    fun dao(): T
}