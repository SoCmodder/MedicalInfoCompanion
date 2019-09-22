package world.mitchmiller.medicalinfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import world.mitchmiller.medicalinfo.db.repo.AppointmentRepository
import world.mitchmiller.medicalinfo.db.MyRoomDatabase
import world.mitchmiller.medicalinfo.db.model.Appointment

class MedicalItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AppointmentRepository
    val allMedicalItems: LiveData<List<Appointment>>

    init {
        val medItemDao = MyRoomDatabase.getDatabase(application, viewModelScope).medItemDao()
        repository = AppointmentRepository(medItemDao)
        allMedicalItems = repository.allAppointments
    }

    fun insert(appointment: Appointment) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(appointment)
    }

    fun delete(appointment: Appointment) {
        repository.delete(appointment)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun updateMedicalItem(appointment: Appointment) = viewModelScope.launch(Dispatchers.IO){
        repository.updateMedicalItem(appointment)
    }
}