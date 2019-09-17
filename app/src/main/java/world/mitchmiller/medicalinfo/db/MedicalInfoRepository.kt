package world.mitchmiller.medicalinfo.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import world.mitchmiller.medicalinfo.db.model.MedicalItem

class MedicalInfoRepository(private val medItemDao: MedItemDao) {
    val allMedicalItems: LiveData<List<MedicalItem>> = medItemDao.getAllMedItems()

    @WorkerThread
    suspend fun insert(medItem: MedicalItem) {
        medItemDao.insert(medItem)
    }

    @WorkerThread
    fun delete(medItem: MedicalItem) {
        medItemDao.delete(medItem)
    }

    @WorkerThread
    fun deleteAll() {
        medItemDao.deleteAll()
    }

    @WorkerThread
    suspend fun updateMedicalItem(medItem: MedicalItem) {
        medItemDao.update(medItem)
    }
}