package dev.unit6.healthypets.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtils {

    private const val DEFAULT_DATE_MASK = "dd.MM.yyyy"

    private val formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_MASK)

    /**
     * Default value formatter = "dd.MM.yyyy"
     */
    fun getStringData(year: Int, monthOfYear: Int, dayOfMonth: Int): String {
        val date = LocalDate.of(year, monthOfYear, dayOfMonth)
        return formatter.format(date)
    }
}
