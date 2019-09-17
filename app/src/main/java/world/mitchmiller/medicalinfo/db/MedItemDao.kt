package world.mitchmiller.medicalinfo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import world.mitchmiller.medicalinfo.db.model.MedicalItem

@Dao
interface MedItemDao {

    @Query("SELECT * from medical_item_table ORDER BY name ASC")
    fun getAllMedItems(): LiveData<List<MedicalItem>>

    @Insert
    suspend fun insert(medicalItem: MedicalItem)

    @Query("DELETE FROM medical_item_table")
    fun deleteAll()

    @Delete
    fun delete(medicalItem: MedicalItem)

    @Update
    suspend fun update(medicalItem: MedicalItem)
}