package world.mitchmiller.medicalinfo.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctor_info_table")
data class DoctorInfo(
    @PrimaryKey @ColumnInfo(name = "name") val name: String
)