package dev.unit6.healthypets.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.FileInputStream


object ImageUtils {
    private const val BUFFER_SIZE = 1024
    fun savePhotoToGallery(filePath: String, contentResolver: ContentResolver) {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
        val uri =
            contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )
        FileInputStream(filePath).use { input ->
            if (uri != null) {
                contentResolver.openOutputStream(uri).use { output ->
                    val buffer = ByteArray(BUFFER_SIZE)
                    while (true) {
                        val numBytesRead = input.read(buffer)
                        if (numBytesRead <= 0) {
                            break
                        }
                        output?.write(buffer, 0, numBytesRead)
                    }
                }
            }
        }
    }

    fun getBitmapByUrl(contentResolver: ContentResolver, imageUri: Uri): Bitmap? {
        val inputStream = contentResolver.openInputStream(imageUri)
        return inputStream?.let {
            BitmapFactory.decodeStream(inputStream)
        }
    }
    fun getExifByUri(imageUri: Uri,contentResolver: ContentResolver): ExifInterface? {
        contentResolver.openInputStream(imageUri)?.use { inputStream ->
            return ExifInterface(inputStream)
        }
        return null
    }
}
