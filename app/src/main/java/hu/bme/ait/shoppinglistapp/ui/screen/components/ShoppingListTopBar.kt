package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.Category
import hu.bme.ait.shoppinglistapp.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListTopBar(
    title: String,
    onAddClick: (() -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null,
    onNavigateBack: (() -> Unit)? = null,

    categoryList: List<Category>? = null,
    selectedCategory: Category? = null,
    onCategorySelected: ((Category) -> Unit)? = null,
    itemLabel: ((Category) -> String)? = null
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple40,
            titleContentColor = Color.White
        ),
        actions = {
            if (onAddClick != null) IconButton(onClick = onAddClick) {
                Icon(Icons.Filled.AddCircle,
                    stringResource(R.string.btn_add_item),
                    tint = Color.White)
            }
            if (onDeleteClick != null)  IconButton(onClick = onDeleteClick) {
                Icon(Icons.Filled.Delete,
                    stringResource(R.string.btn_delete_item),
                    tint = Color.White)
            }
            if (categoryList != null && selectedCategory != null &&
                onCategorySelected != null && itemLabel != null) {
                TopBarSpinner(
                    list = categoryList,
                    preselected = selectedCategory,
                    onSelectChange = onCategorySelected,
                    itemLabel = itemLabel
                )
            }
        },
        navigationIcon = {
            if (onNavigateBack != null) {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack,
                        stringResource(R.string.btn_back))
                }
            }
        }
    )
}