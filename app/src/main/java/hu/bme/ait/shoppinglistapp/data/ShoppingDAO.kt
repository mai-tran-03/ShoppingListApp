package hu.bme.ait.shoppinglistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDAO {
    @Query("SELECT * FROM shoppingTable WHERE id = :id")
    fun getItem(id: Int): Flow<ShoppingItem>

    @Query("SELECT * FROM shoppingTable")
    fun getAllItems(): Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shoppingTable WHERE category= :category")
    fun getAllItemByCategory(category: Category): Flow<List<ShoppingItem>>

    @Query("SELECT COUNT(*) FROM shoppingTable")
    suspend fun getTotalItemCount(): Int

    @Query("SELECT COUNT(*) FROM shoppingTable WHERE category= :category")
    suspend fun getItemCountByCategory(category: Category): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ShoppingItem)

    @Update
    suspend fun update(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("DELETE FROM shoppingTable")
    suspend fun deleteAllItems()
}