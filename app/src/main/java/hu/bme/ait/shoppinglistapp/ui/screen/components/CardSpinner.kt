package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hu.bme.ait.shoppinglistapp.data.Category

@Composable
fun CardSpinner(
    list: List<Category>,
    preselected: Category,
    onSelectChange: (Category) -> Unit,
    itemLabel: (Category) -> String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(preselected) }

    OutlinedCard(
        modifier = modifier
            .clickable { expanded = true }
            .fillMaxWidth()
            .padding(bottom = 15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = itemLabel(selected),
                modifier = Modifier.weight(1f).padding(16.dp)
            )
            Icon(Icons.Outlined.ArrowDropDown, null)
        }

        CategoryDropdownMenu(
            list = list,
            selected = selected,
            onSelectChange = { selected = it; onSelectChange(it) },
            itemLabel = itemLabel,
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.7f)
        )
    }
}