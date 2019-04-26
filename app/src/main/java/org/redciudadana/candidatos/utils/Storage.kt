package eleccionmp.redciudadana.org.eleccionmp.utils

import android.content.Context
import android.content.SharedPreferences
import java.io.FileNotFoundException

/**
 * Created by javier on 2/5/18.
 */

object Storage {
    fun getFileName(key: String) = "$key.json"

    fun getStringPreference(context: Context, key: String): String? {
        try {
            val bufferedReader = context.openFileInput(getFileName(key)).bufferedReader()
            return bufferedReader.use { it.readText() }
        } catch (e: FileNotFoundException) {
            return null
        }
    }

    fun setStringPreference(context: Context, key: String, value: String) {
        val writer = context.openFileOutput(getFileName(key), Context.MODE_PRIVATE).bufferedWriter()
        writer.use {
            it.append(value)
        }
    }
}