package hu.bme.ait.shoppinglistapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.Category
import hu.bme.ait.shoppinglistapp.data.ShoppingItem
import hu.bme.ait.shoppinglistapp.ui.screen.components.DeleteDialog
import hu.bme.ait.shoppinglistapp.ui.screen.components.ItemDialog
import hu.bme.ait.shoppinglistapp.ui.screen.components.ShoppingListContent
import hu.bme.ait.shoppinglistapp.ui.screen.components.ShoppingListTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    navController: NavHostController,
    shoppingListViewModel: ShoppingListViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var showItemDialog by rememberSaveable { mutableStateOf(false) }
    var showDeleteDialog by rememberSaveable { mutableStateOf(false) }
    var editItem: ShoppingItem? by rememberSaveable { mutableStateOf(null) }
    var selectedCategory by remember { mutableStateOf(Category.OTHER) }

    val shoppingList = shoppingListViewModel.getAllItems().collectAsState(emptyList())

    Column(modifier = Modifier.fillMaxWidth()) {
        ShoppingListTopBar(
            title = context.getString(R.string.txt_shopping_list),
            onAddClick = { showItemDialog = true },
            onDeleteClick = { showDeleteDialog = true },
            categoryList = Category.entries,
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                selectedCategory = category
                navController.navigate(
                    context.getString(R.string.route_filtered_shopping_list)
                        .replace(
                            oldValue = "{${context.getString( R.string.category)}}",
                            newValue = category.name
                        ))
            },
            itemLabel = { category -> category.displayName }
        )
        ShoppingListContent(
            shoppingList = shoppingList.value,
            shoppingListViewModel = shoppingListViewModel
        )

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
}