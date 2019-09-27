package world.mitchmiller.medicalinfo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import world.mitchmiller.medicalinfo.db.dao.AppointmentDao
import world.mitchmiller.medicalinfo.db.dao.DoctorDao
import world.mitchmiller.medicalinfo.db.dao.DocumentDao
import world.mitchmiller.medicalinfo.db.model.Appointment
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.db.model.Document

@Database(entities = [Appointment::class, Doctor::class, Document::class], version = 2)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun medItemDao(): AppointmentDao
    abstract fun doctorDao(): DoctorDao
    abstract fun documentDao(): DocumentDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MyRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDatabase::class.java,
                    "Medical_Info_APP_database"
                )
                    .addCallback(MedInfoDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    private class MedInfoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    //populateInitialSettings(database.settingDao())
                }
            }
        }

        // insert default settings
//        private suspend fun populateInitialSettings(dao: SettingDao) {
//            if (dao.getAllSettings().value?.size == 0) {
//                dao.insert(Setting("settings_version", "1", false))
//                dao.insert(Setting("income", "0.00", false))
//            }
//        }
    }
}