package dev.unit6.healthypets

import android.content.Context
import android.content.res.Resources.NotFoundException
import java.io.IOException
import java.util.Properties

object ConfigHelper {
    fun getConfigValue(context: Context, name: String?): String {
        val resources = context.resources
        try {
            val rawResource = resources.openRawResource(R.raw.config)
            val properties = Properties()
            properties.load(rawResource)
            return properties.getProperty(name)
                ?: throw NotFoundException("Value $name not found in configuration file")
        } catch (e: NotFoundException) {
            throw NotFoundException("Unable to find the config file: $e.message")
        } catch (e: IOException) {
            throw Exception("Failed to open config file.")
        }
    }
}
