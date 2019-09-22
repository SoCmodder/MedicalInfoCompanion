package world.mitchmiller.medicalinfo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import world.mitchmiller.medicalinfo.db.MyRoomDatabase
import world.mitchmiller.medicalinfo.db.model.Document
import world.mitchmiller.medicalinfo.db.repo.DocumentRepository

class DocumentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DocumentRepository
    val allDocuments: LiveData<List<Document>>

    init {
        val documentDao = MyRoomDatabase.getDatabase(application, viewModelScope).documentDao()
        repository = DocumentRepository(documentDao)
        allDocuments = repository.allDocuments
    }

    fun insert(document: Document) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(document)
    }

    fun delete(document: Document) {
        repository.delete(document)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun updateMedicalItem(document: Document) = viewModelScope.launch(Dispatchers.IO){
        repository.updateDocument(document)
    }
}