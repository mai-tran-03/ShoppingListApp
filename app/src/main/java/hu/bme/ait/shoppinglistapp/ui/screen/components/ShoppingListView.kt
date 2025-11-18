package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hu.bme.ait.shoppinglistapp.data.ShoppingItem
import hu.bme.ait.shoppinglistapp.ui.theme.PurpleGrey80

@Composable
fun ShoppingListView(
    shoppingList: List<ShoppingItem>,
    onItemChecked: (ShoppingItem, Boolean) -> Unit,
    onItemEdit: (ShoppingItem) -> Unit,
    onItemDelete: (ShoppingItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(color = PurpleGrey80)
    ) {
        items(shoppingList) { item ->
            ItemCard(item,
                onItemChecked = onItemChecked,
                onItemEdit = onItemEdit,
                onItemDelete = onItemDelete
            )
        }
    }
}