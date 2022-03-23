package com.stslex93.notes.ui.utils.real

import com.stslex93.notes.ui.utils.interf.TimeUtil
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