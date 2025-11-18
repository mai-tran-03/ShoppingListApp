package hu.bme.ait.shoppinglistapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.Category
import hu.bme.ait.shoppinglistapp.ui.screen.components.ShoppingListContent
import hu.bme.ait.shoppinglistapp.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredShoppingListScreen(
    navController: NavHostController,
    filteredCategory: Category,
    shoppingListViewModel: ShoppingListViewModel = hiltViewModel()
) {
    val shoppingList = shoppingListViewModel
        .getAllItemsByCategory(category = filteredCategory)
        .collectAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(
                stringResource(R.string.txt_shopping_list_with_category,
                    stringResource(R.string.txt_shopping_list),
                    filteredCategory.displayName)
            ) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Purple40,
                titleContentColor = Color.White
            ),
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack,
                        stringResource(R.string.btn_back),
                        tint = Color.White
                    )
                }
            }
        )
        ShoppingListContent(
            shoppingList = shoppingList.value,
            shoppingListViewModel = shoppingListViewModel
        )
    }
}