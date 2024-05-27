package dev.unit6.healthypets.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
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

    fun updatePhoto(
        success: Boolean,
        context: Context,
        photo: File?,
        providerBundle: String,
        callback: (uri:Uri) -> Unit
    ) {
        if (success && photo != null) {
            val photoUri = FileProvider.getUriForFile(
                context,
                providerBundle,
                photo
            )
            callback(photoUri)
            ImageUtils.savePhotoToGallery(
                photo.absolutePath,
                context.contentResolver
            )
        } else {
            Toast.makeText(context, "Failed to capture image", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
