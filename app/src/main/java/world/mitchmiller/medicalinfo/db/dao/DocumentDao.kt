package world.mitchmiller.medicalinfo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import world.mitchmiller.medicalinfo.db.model.Document

@Dao
interface DocumentDao {
    @Query("SELECT * from document_table ORDER BY id ASC")
    fun getAllDocuments(): LiveData<List<Document>>

    @Insert
    suspend fun insert(document: Document)

    @Query("DELETE FROM document_table")
    fun deleteAll()

    @Delete
    fun delete(document: Document)

    @Update
    suspend fun update(document: Document)
}