package dev.unit6.healthypets.extension

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import dev.unit6.healthypets.utils.ImageUtils

object BitmapExtension {
    fun Bitmap.modifyOrientationBitmap(imageUri: Uri, contentResolver: ContentResolver): Bitmap {
        val orientation =
            ImageUtils.getExifByUri(imageUri, contentResolver)?.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> this.rotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> this.rotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> this.rotate(270f)
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> this.flip(
                horizontal = true,
                vertical = false
            )

            ExifInterface.ORIENTATION_FLIP_VERTICAL -> this.flip(
                horizontal = false,
                vertical = true
            )

            else -> this
        }
    }


    private fun Bitmap.rotate(degrees: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degrees)
        return Bitmap.createBitmap(
            this,
            0,
            0,
            this.getWidth(),
            this.getHeight(),
            matrix,
            true
        )
    }

    private fun Bitmap.flip(horizontal: Boolean, vertical: Boolean): Bitmap {
        val matrix = Matrix()
        matrix.preScale(if (horizontal) -1f else 1f, if (vertical) -1f else 1f)
        return Bitmap.createBitmap(
            this,
            0,
            0,
            this.getWidth(),
            this.getHeight(),
            matrix,
            true
        )
    }
}
