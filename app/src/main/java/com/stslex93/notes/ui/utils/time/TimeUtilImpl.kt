package com.stslex93.notes.ui.utils.time

import java.text.SimpleDateFormat
import java.util.Locale

class TimeUtilImpl : TimeUtil {

    override fun getCurrentTime(): String {
        val currentTimeInMs = System.currentTimeMillis()
        val locale = Locale.getDefault()
        return SimpleDateFormat(TIME_FORMAT, locale).format(currentTimeInMs)
    }

    companion object {
        private const val TIME_FORMAT = "kk.mm"
    }
}