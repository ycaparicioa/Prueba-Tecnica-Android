import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    @Query("SELECT * FROM usuario_local LIMIT 1")
    suspend fun getLoggedUser(): UserEntity?
    @Query("DELETE FROM usuario_local")
    suspend fun clearUserData()
}
