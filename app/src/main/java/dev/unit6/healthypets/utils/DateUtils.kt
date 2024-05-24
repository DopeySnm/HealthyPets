package dev.unit6.healthypets.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object DateUtils {

    private const val DEFAULT_DATE_MASK = "dd.MM.yyyy"
    private const val IMAGE_DATE_MASK = "yyyyMMdd_HHmmss"

    /**
     * Default value formatter = "dd.MM.yyyy"
     */
    fun getStringData(year: Int, monthOfYear: Int, dayOfMonth: Int): String {
        val formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_MASK)
        val date = LocalDate.of(year, monthOfYear, dayOfMonth)
        return formatter.format(date)
    }

    fun convertDateToImageFormat(date: Date): String {
        val formatter = SimpleDateFormat(IMAGE_DATE_MASK, Locale.ROOT)
        return formatter.format(date)
    }
}
