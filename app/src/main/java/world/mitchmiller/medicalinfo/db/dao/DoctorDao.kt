package world.mitchmiller.medicalinfo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import world.mitchmiller.medicalinfo.db.model.Doctor

@Dao
interface DoctorDao {
    @Query("SELECT * from doctor_table ORDER BY id ASC")
    fun getAllDoctors(): LiveData<List<Doctor>>

    @Insert
    suspend fun insert(doctor: Doctor)

    @Query("DELETE FROM appointment_table")
    fun deleteAll()

    @Delete
    fun delete(doctor: Doctor)

    @Update
    suspend fun update(doctor: Doctor)

    @Query("SELECT * FROM doctor_table WHERE id = :id")
    fun getDoctorById(id: Int): LiveData<Doctor>
}