package world.mitchmiller.medicalinfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import world.mitchmiller.medicalinfo.db.MyRoomDatabase
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.db.repo.DoctorRepository

class DoctorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DoctorRepository
    val allDoctors: LiveData<List<Doctor>>

    init {
        val doctorDao = MyRoomDatabase.getDatabase(application, viewModelScope).doctorDao()
        repository = DoctorRepository(doctorDao)
        allDoctors = repository.allDoctors
    }

    fun insert(doctor: Doctor) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(doctor)
    }

    fun delete(doctor: Doctor) {
        repository.delete(doctor)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun updateDoctor(doctor: Doctor) = viewModelScope.launch(Dispatchers.IO){
        repository.updateDoctor(doctor)
    }

    fun getDoctorById(id: Int): LiveData<Doctor> {
        return repository.getDoctorById(id)
    }
}