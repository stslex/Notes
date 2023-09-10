package com.stslex93.notes.core.label.di

import com.stslex93.notes.core.label.repository.LabelRepository

interface LabelApi {

    val repository: LabelRepository
}