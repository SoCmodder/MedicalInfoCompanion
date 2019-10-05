package world.mitchmiller.medicalinfo.util

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*

object FileUtils {

    public fun createDirectoryAndSaveBitmapFile(imageToSave: Bitmap, filename: String) {
        val dirName = "/medical_info_companion"
        val dir = File(Environment.getExternalStorageDirectory().path + dirName)

        if (!dir.exists()) {
            val medInfoDir = File("/sdcard"+dirName)
            medInfoDir.mkdirs()
        }

        val file = File("/sdcard"+dirName, filename)
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            Log.e("FileUtils", e.message)
        }
    }

    public fun createImageFileName(): String {
        val randomMathVal = Math.random()
        val randomCalVal = Calendar.getInstance().get(Calendar.MILLISECOND)
        return "doc_image" + randomMathVal.toString() + randomCalVal.toString()
    }

}