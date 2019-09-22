package world.mitchmiller.medicalinfo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import world.mitchmiller.medicalinfo.db.model.Appointment

@Dao
interface AppointmentDao {
    @Query("SELECT * from appointment_table ORDER BY name ASC")
    fun getAllAppointments(): LiveData<List<Appointment>>

    @Insert
    suspend fun insert(appointment: Appointment)

    @Query("DELETE FROM appointment_table")
    fun deleteAll()

    @Delete
    fun delete(appointment: Appointment)

    @Update
    suspend fun update(appointment: Appointment)
}