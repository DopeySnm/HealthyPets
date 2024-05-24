package dev.unit6.healthypets.utils

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.IOException
import java.util.Date

object CameraUtils {
    fun takePicture(context: Context): File? {
        val photoFile: File? = try {
            createTempImage(context)
        } catch (ex: IOException) {
            Toast.makeText(context, "Error occurred while creating the file", Toast.LENGTH_SHORT)
                .show()
            null
        }
        return photoFile
    }

    private fun createTempImage(context: Context): File {
        val timeStamp: String = DateUtils.convertDateToImageFormat(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "hp_${timeStamp}",
            ".png",
            storageDir
        )
    }
}
