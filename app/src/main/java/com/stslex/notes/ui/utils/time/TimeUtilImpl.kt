package com.stslex.notes.ui.utils.time

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TimeUtilImpl @Inject constructor() : TimeUtil {

    override fun getCurrentTime(): String {
        val currentTimeInMs = System.currentTimeMillis()
        val locale = Locale.getDefault()
        return SimpleDateFormat(TIME_FORMAT, locale).format(currentTimeInMs)
    }

    companion object {
        private const val TIME_FORMAT = "kk.mm"
    }
}