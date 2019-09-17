package world.mitchmiller.medicalinfo.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medical_info_document_table")
data class MedicalInfoDocument(
    @PrimaryKey @ColumnInfo(name = "name") val name: String
)