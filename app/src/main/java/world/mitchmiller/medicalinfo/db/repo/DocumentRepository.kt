package world.mitchmiller.medicalinfo.db.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import world.mitchmiller.medicalinfo.db.dao.DocumentDao
import world.mitchmiller.medicalinfo.db.model.Document

class DocumentRepository(private val documentDao: DocumentDao) {
    val allDocuments: LiveData<List<Document>> = documentDao.getAllDocuments()

    @WorkerThread
    suspend fun insert(doc: Document) {
        documentDao.insert(doc)
    }

    @WorkerThread
    fun delete(doc: Document) {
        documentDao.delete(doc)
    }

    @WorkerThread
    fun deleteAll() {
        documentDao.deleteAll()
    }

    @WorkerThread
    suspend fun updateDocument(doc: Document) {
        documentDao.update(doc)
    }
}