package world.mitchmiller.medicalinfo.db.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import world.mitchmiller.medicalinfo.db.dao.AppointmentDao
import world.mitchmiller.medicalinfo.db.model.Appointment

class AppointmentRepository(private val appointmentDao: AppointmentDao) {
    val allAppointments: LiveData<List<Appointment>> = appointmentDao.getAllAppointments()

    @WorkerThread
    suspend fun insert(appt: Appointment) {
        appointmentDao.insert(appt)
    }

    @WorkerThread
    fun delete(appt: Appointment) {
        appointmentDao.delete(appt)
    }

    @WorkerThread
    fun deleteAll() {
        appointmentDao.deleteAll()
    }

    @WorkerThread
    suspend fun updateMedicalItem(appt: Appointment) {
        appointmentDao.update(appt)
    }
}