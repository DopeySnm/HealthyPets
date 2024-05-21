package dev.unit6.healthypets

import java.text.SimpleDateFormat

object DateHelper {
    private val formatter = SimpleDateFormat("dd.MM.yyyy")

    fun getDateInFormatted(dateFloat: Float): String {
        if (dateFloat == 0.0f) {
            return ""
        }
        return formatter.format(dateFloat)
    }

    /**
     * Default value formatter = "dd.MM.yyyy"
     */
    fun dateToFloat(dateFormat: SimpleDateFormat = formatter, dateString: String): Float {
        val date = kotlin.runCatching {
            dateFormat.parse(dateString)
        }.fold(
            onSuccess = {
                it
            },
            onFailure = {
                return 0.0f
            }
        )

        return date?.time?.toFloat() ?: 0f
    }
}
