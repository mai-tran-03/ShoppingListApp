package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.Category

@Composable
fun TopBarSpinner(
    list: List<Category>,
    preselected: Category,
    onSelectChange: (Category) -> Unit,
    itemLabel: (Category) -> String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(preselected) }

    Box(modifier = modifier) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Filled.MoreVert,
                stringResource(R.string.btn_filter_items),
                tint = Color.White)
        }

        CategoryDropdownMenu(
            list = list,
            selected = selected,
            onSelectChange = { selected = it; onSelectChange(it) },
            itemLabel = itemLabel,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        )
    }
}