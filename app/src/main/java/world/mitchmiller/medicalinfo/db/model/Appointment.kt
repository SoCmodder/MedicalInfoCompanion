package world.mitchmiller.medicalinfo.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment_table")
data class Appointment(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    val doctorId: Int
)
