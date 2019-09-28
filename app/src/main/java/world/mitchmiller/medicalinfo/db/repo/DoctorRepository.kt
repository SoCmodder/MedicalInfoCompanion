package world.mitchmiller.medicalinfo.db.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import world.mitchmiller.medicalinfo.db.dao.DoctorDao
import world.mitchmiller.medicalinfo.db.model.Doctor

class DoctorRepository(private val doctorDao: DoctorDao) {
    val allDoctors: LiveData<List<Doctor>> = doctorDao.getAllDoctors()

    @WorkerThread
    suspend fun insert(doc: Doctor) {
        doctorDao.insert(doc)
    }

    @WorkerThread
    fun delete(doc: Doctor) {
        doctorDao.delete(doc)
    }

    @WorkerThread
    fun deleteAll() {
        doctorDao.deleteAll()
    }

    @WorkerThread
    suspend fun updateDoctor(doc: Doctor) {
        doctorDao.update(doc)
    }

    @WorkerThread
    fun getDoctorById(id: Int): LiveData<Doctor> {
        return doctorDao.getDoctorById(id)
    }
}