package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.Category

@Composable
fun CategoryDropdownMenu(
    list: List<Category>,
    selected: Category,
    onSelectChange: (Category) -> Unit,
    itemLabel: (Category) -> String,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier
    ) {
        list.forEachIndexed { index, listEntry ->
            DropdownMenuItem(
                onClick = { onSelectChange(listEntry); onDismissRequest() },
                leadingIcon = {
                    Image(
                        painter = painterResource(id = listEntry.getIcon()),
                        contentDescription = stringResource(R.string.txt_item_category),
                        modifier = Modifier.size(24.dp)
                    )
                },
                text = { Text(itemLabel(listEntry)) }
            )
            if (index < list.lastIndex)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}