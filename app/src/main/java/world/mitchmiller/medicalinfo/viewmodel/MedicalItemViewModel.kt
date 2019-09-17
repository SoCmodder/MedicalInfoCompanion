package world.mitchmiller.medicalinfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import world.mitchmiller.medicalinfo.db.MedicalInfoRepository
import world.mitchmiller.medicalinfo.db.MedicalInfoRoomDatabase
import world.mitchmiller.medicalinfo.db.model.MedicalItem

class MedicalItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MedicalInfoRepository
    val allMedicalItems: LiveData<List<MedicalItem>>

    init {
        val medItemDao = MedicalInfoRoomDatabase.getDatabase(application, viewModelScope).medItemDao()
        repository = MedicalInfoRepository(medItemDao)
        allMedicalItems = repository.allMedicalItems
    }

    fun insert(medicalItem: MedicalItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(medicalItem)
    }

    fun delete(medicalItem: MedicalItem) {
        repository.delete(medicalItem)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun updateMedicalItem(medicalItem: MedicalItem) = viewModelScope.launch(Dispatchers.IO){
        repository.updateMedicalItem(medicalItem)
    }
}