package hu.bme.ait.shoppinglistapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.ait.shoppinglistapp.data.Category
import hu.bme.ait.shoppinglistapp.data.ShoppingDAO
import hu.bme.ait.shoppinglistapp.data.ShoppingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(val shoppingDAO: ShoppingDAO): ViewModel() {
    suspend fun getTotalItemCount(): Int {
        return shoppingDAO.getTotalItemCount()
    }

    suspend fun getItemCountByCategory(category: Category): Int {
        return  shoppingDAO.getItemCountByCategory(category)
    }

    fun getItem(id: Int): Flow<ShoppingItem> {
        return shoppingDAO.getItem(id)
    }

    fun getAllItems(): Flow<List<ShoppingItem>> {
        return shoppingDAO.getAllItems()
    }

    fun getAllItemsByCategory(category: Category): Flow<List<ShoppingItem>> {
        return shoppingDAO.getAllItemByCategory(category)
    }

    fun addItem(item: ShoppingItem) {
        viewModelScope.launch { shoppingDAO.insert(item) }
    }

    fun updateItem(item: ShoppingItem) {
        viewModelScope.launch { shoppingDAO.update(item) }
    }

    fun removeItem(item: ShoppingItem) {
        viewModelScope.launch { shoppingDAO.delete(item) }
    }

    fun removeAllItems() {
        viewModelScope.launch { shoppingDAO.deleteAllItems() }
    }

    fun updateItemStatus(item: ShoppingItem, status: Boolean) {
        val updatedItem = item.copy()
        updatedItem.status = status
        viewModelScope.launch { shoppingDAO.update(updatedItem) }
    }
}