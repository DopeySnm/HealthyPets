package dev.unit6.healthypets.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtils {

    private const val DATE_FORMAT = "dd.MM.yyyy"

    private val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)

    /**
     * Default value formatter = "dd.MM.yyyy"
     */
    fun getStringData(year: Int, monthOfYear: Int, dayOfMonth: Int): String {
        val date = LocalDate.of(year, monthOfYear, dayOfMonth)
        return formatter.format(date)
    }
}
