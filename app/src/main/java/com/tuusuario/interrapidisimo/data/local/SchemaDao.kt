import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SchemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTables(tables: List<SchemaTableEntity>)
    @Query("SELECT * FROM tablas_esquema")
    suspend fun getAllTables(): List<SchemaTableEntity>
    @Query("DELETE FROM tablas_esquema")
    suspend fun clearTables()
}
