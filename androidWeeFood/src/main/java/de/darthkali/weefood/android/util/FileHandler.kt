package de.darthkali.weefood.android.util

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel
import android.provider.MediaStore
import com.google.android.material.internal.ContextUtils
import com.google.android.material.internal.ContextUtils.getActivity
import java.lang.Exception
import android.provider.OpenableColumns
import androidx.annotation.NonNull
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.InputStream
import java.io.OutputStream


object FileHandler {

    /**
     * copy contents from source file to destination file
     *
     * @param sourceFilePath  Source file path address
     * @param destinationFilePath Destination file path address
     */
    fun copyFile(sourceFilePath: File, destinationFilePath: File) {
        try {
            if (!sourceFilePath.exists()) {
                return
            }
            var source: FileChannel? = null
            var destination: FileChannel? = null
            source = FileInputStream(sourceFilePath).channel
            destination = FileOutputStream(destinationFilePath).channel
            if (destination != null && source != null) {
                destination.transferFrom(source, 0, source.size())
            }

            source?.close()
            destination?.close()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun getFileFromUri(uri: Uri?, context: Context): File? {
        var file: File? = null
        uri?.let {
            val path = RealPathUtil.getDataColumn(context, it, null, null)
            if (path != null) {
                file = File(path)
            }
        }
        return file
    }




    @Throws(java.lang.Exception::class)
    fun saveFile(src: File, context: Context): File {

        val dest = createDownloadFile(context)

        val input = FileInputStream(src)
        val output = FileOutputStream(dest)
        copy(input, output)
        return dest
    }

    fun createDownloadFile(context: Context): File {
        val imageFileName = "Recipe_image_" + System.currentTimeMillis()
        return File(context.filesDir, imageFileName)
    }

    @Throws(java.lang.Exception::class)
    private fun copy(input: InputStream, output: OutputStream): Int {
        val BUFFER_SIZE = 1024 * 2
        val buffer = ByteArray(BUFFER_SIZE)
        val `in` = BufferedInputStream(input, BUFFER_SIZE)
        val out = BufferedOutputStream(output, BUFFER_SIZE)
        var count = 0
        var n: Int
        try {
            while (`in`.read(buffer, 0, BUFFER_SIZE).also { n = it } != -1) {
                out.write(buffer, 0, n)
                count += n
            }
            out.flush()
        } finally {
            out.close()
            `in`.close()
        }
        return count
    }
}