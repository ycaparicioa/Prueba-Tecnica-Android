import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Entity(tableName = "usuario_local")
data class UserEntity(
    @PrimaryKey val identification: String, // [cite: 36]
    val usuario: String,                    // [cite: 35]
    val nombre: String                      // [cite: 37]
)

@Entity(tableName = "tablas_esquema")
data class SchemaTableEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreTabla: String                 // 
)
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Query("SELECT * FROM usuario_local LIMIT 1")
    suspend fun getUser(): UserEntity?
}
@Dao
interface SchemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTables(tables: List<SchemaTableEntity>)

    @Query("SELECT * FROM tablas_esquema")
    suspend fun getAllTables(): List<SchemaTableEntity>
}


@Database(entities = [UserEntity::class, SchemaTableEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun schemaDao(): SchemaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "interrapidisimo_db" // 
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
