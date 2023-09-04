package com.stslex93.notes.core.label.di

import com.stslex93.notes.core.database.label.LabelDao

interface LabelDependencies {

    val labelDao: LabelDao
}