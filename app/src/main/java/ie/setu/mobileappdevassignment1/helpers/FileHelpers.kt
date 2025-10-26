package ie.setu.mobileappdevassignment1.helpers

import android.content.Context
import mu.KotlinLogging
import java.io.*
import timber.log.Timber

val logger = KotlinLogging.logger {}

fun write(context: Context, fileName: String, data: String) {
    try {
        // THIS IS THE FIX: context.openFileOutput() automatically gets a stream
        // to a private, writable file in your app's 'files' directory.
        val outputStreamWriter = OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
        Timber.i("Successfully wrote to file: $fileName")
    } catch (e: Exception) {
        Timber.e("Cannot write to file: %s", e.toString())
    }
}

fun read(context: Context, fileName: String): String {
    val file = File(fileName)
    var str = ""
    try {
        val inputStream = context.openFileInput(fileName)
        val inputStreamReader = InputStreamReader(FileInputStream(file))
        if (inputStreamReader != null) {
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                val line = bufferedReader.readLine()
                done = (line == null);
                if (line != null) partialStr.append(line);
            }
            inputStreamReader.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        logger.error { "Cannot Find file: $e" }
    } catch (e: IOException) {
        logger.error { "Cannot Read file: $e"  }
    }
    return str
}

fun exists(context: Context, fileName: String): Boolean {
    val file = context.getFileStreamPath(fileName)
    return file.exists()
}
