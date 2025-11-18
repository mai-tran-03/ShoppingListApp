package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.ShoppingItem
import hu.bme.ait.shoppinglistapp.ui.screen.ShoppingListViewModel

@Composable
fun DeleteDialog(
    viewModel: ShoppingListViewModel,
    item: ShoppingItem?,
    onCancel: () -> Unit
) {
    Dialog(onDismissRequest = { onCancel() }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 4.dp)
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(stringResource(R.string.txt_alert))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        if (item == null) viewModel.removeAllItems()
                        else viewModel.removeItem(item)
                        onCancel()
                    }) { Text(stringResource(R.string.btn_yes)) }
                }
            }
        }
    }
}