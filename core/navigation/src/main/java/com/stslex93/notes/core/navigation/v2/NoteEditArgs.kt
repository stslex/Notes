package com.stslex93.notes.core.navigation.v2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteEditArgs(
    val noteId: Int,
    val isEdit: Boolean
) : Parcelable