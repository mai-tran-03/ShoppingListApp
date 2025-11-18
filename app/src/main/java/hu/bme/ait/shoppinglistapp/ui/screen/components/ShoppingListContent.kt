package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import hu.bme.ait.shoppinglistapp.data.ShoppingItem
import hu.bme.ait.shoppinglistapp.ui.screen.ShoppingListViewModel

@Composable
fun ShoppingListContent(
    shoppingList: List<ShoppingItem>,
    shoppingListViewModel: ShoppingListViewModel
) {
    var showItemDialog by rememberSaveable { mutableStateOf(false) }
    var showDeleteDialog by rememberSaveable { mutableStateOf(false) }
    var editItem: ShoppingItem? by rememberSaveable { mutableStateOf(null) }

    if (shoppingList.isEmpty()) {
        EmptyListView()
    } else {
        ShoppingListView(
            shoppingList = shoppingList,
            onItemChecked = { item, checked ->
                shoppingListViewModel.updateItemStatus(item, checked)
            },
            onItemEdit = { selectedItem ->
                editItem = selectedItem
                showItemDialog = true
            },
            onItemDelete = { selectedItem ->
                editItem = selectedItem
                showDeleteDialog = true
            }
        )
    }

    if (showDeleteDialog) {
        DeleteDialog(
            shoppingListViewModel,
            editItem,
            onCancel = { showDeleteDialog = false }
        )
    }

    if (showItemDialog) {
        ItemDialog(
            shoppingListViewModel,
            editItem,
            onCancel = {
                showItemDialog = false
                editItem = null
            }
        )
    }
}