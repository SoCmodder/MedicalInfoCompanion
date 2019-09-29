package world.mitchmiller.medicalinfo.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun fromMillisToTimeString(millis: Long) : String {
        val format = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
        return format.format(millis)
    }

    fun getDBDateString(millis: Long) : String {
        val dbFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.getDefault())
        return dbFormat.format(millis)
    }

    fun getDBTimeString(millis: Long) : String {
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(millis)
    }

    fun fromDBDateString(dbDateString: String) : String {
        val displayFormat = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
        return displayFormat.format(dbDateString)
    }
}