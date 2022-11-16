package io.oworld.ohchild

import android.content.Context
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.*

@Entity(tableName = "records")
data class Record(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    var date: Long,
    var duration: Float,
    var score: Int
)

@Dao
interface RecordDao {
    @Insert
    fun insert(record: Record)

    @Query("DELETE FROM records")
    fun deleteAll()

    @Query("SELECT * FROM records")
    fun getAll(): LiveData<List<Record>>
}

@Database(entities = [Record::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}

class RecordRepo(private val recordDao: RecordDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allRecords = recordDao.getAll()

    fun insert(record: Record) {
        coroutineScope.launch(Dispatchers.IO) {
            recordDao.insert(record)
        }
    }

    fun deleteAll() {
        coroutineScope.launch(Dispatchers.IO) {
            recordDao.deleteAll()
        }
    }
}