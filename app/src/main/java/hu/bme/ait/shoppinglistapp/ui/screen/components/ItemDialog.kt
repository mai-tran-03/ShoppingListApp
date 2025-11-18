package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.Category
import hu.bme.ait.shoppinglistapp.data.ShoppingItem
import hu.bme.ait.shoppinglistapp.ui.screen.ShoppingListViewModel


@Composable
fun ItemDialog(
    viewModel: ShoppingListViewModel,
    editItem: ShoppingItem?,
    onCancel: () -> Unit
) {
    val context = LocalContext.current

    var itemCategory by remember { mutableStateOf(editItem?.category
        ?: Category.OTHER) }
    var itemName by remember { mutableStateOf(editItem?.name
        ?: context.getString(R.string.txt_empty)) }
    var itemDescription by remember { mutableStateOf(editItem?.description
        ?: context.getString(R.string.txt_empty)) }
    var itemPrice by remember { mutableStateOf(editItem?.estimatedPrice?.toString()
        ?: context.getString(R.string.txt_zero)) }
    var itemStatus by remember { mutableStateOf(editItem?.status ?: false) }

    var nameError by remember { mutableStateOf(false) }
    var priceError by remember { mutableStateOf(false) }

    LaunchedEffect(itemName) {
        nameError = itemName.isBlank()
    }

    Dialog(onDismissRequest = { onCancel() }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 4.dp)
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(
                    if (editItem != null) {
                        context.getString(R.string.txt_edit_item)
                    } else context.getString(R.string.txt_new_item),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                CardSpinner(
                    list = Category.entries,
                    preselected = itemCategory,
                    onSelectChange = { itemCategory = it },
                    itemLabel = { it.displayName }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(context.getString(R.string.txt_item_name)) },
                    value = itemName,
                    onValueChange = {
                        itemName = it
                        nameError = it.isBlank()
                    },
                    singleLine = true,
                    isError = nameError,
                    supportingText = {
                        if (nameError) {
                            Text(
                                context.getString(R.string.err_required_field),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(context.getString(R.string.txt_item_description)) },
                    value = itemDescription,
                    onValueChange = { itemDescription = it },
                    supportingText = {
                        Text(context.getString(R.string.txt_empty))
                    }
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(context.getString(R.string.txt_item_price)) },
                        value = itemPrice,
                        onValueChange = {
                            itemPrice = it
                            priceError = it.isNotEmpty() && it.toIntOrNull() == null
                        },
                        singleLine = true,
                        isError = priceError,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        supportingText = {
                            if (priceError) {
                                Text(
                                    context.getString(R.string.err_valid_number),
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = itemStatus,
                        onCheckedChange = { itemStatus = it }
                    )
                    Text(context.getString(R.string.txt_bought_yet))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        if (editItem == null) {
                            viewModel.addItem(
                                ShoppingItem(
                                    category = itemCategory,
                                    name = itemName,
                                    description = itemDescription,
                                    estimatedPrice = itemPrice.toIntOrNull() ?: 0,
                                    status = itemStatus
                                )
                            )
                        } else {
                            val editedItem = editItem.copy(
                                category = itemCategory,
                                name = itemName,
                                description = itemDescription,
                                estimatedPrice = itemPrice.toIntOrNull() ?: 0,
                                status = itemStatus
                            )
                            viewModel.updateItem(editedItem)
                        }
                        onCancel()
                    },
                        enabled = !nameError
                    ) { Text(context.getString(R.string.btn_save)) }
                }
            }
        }
    }
}