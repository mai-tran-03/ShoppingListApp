package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.ShoppingItem
import hu.bme.ait.shoppinglistapp.ui.theme.Pink80
import hu.bme.ait.shoppinglistapp.ui.theme.Purple80

@Composable
fun ItemCard(
    item: ShoppingItem,
    onItemChecked: (ShoppingItem, Boolean) -> Unit,
    onItemEdit: (ShoppingItem) -> Unit,
    onItemDelete: (ShoppingItem) -> Unit
) {
    Card(
        colors = if (!item.status) {
            CardDefaults.cardColors(
                containerColor = Pink80
            )
        } else {
            CardDefaults.cardColors(
                containerColor = Purple80
            )
        },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Checkbox(
                    checked = item.status,
                    onCheckedChange = { checkState -> onItemChecked(item, checkState) },
                    modifier = Modifier.padding(end = 2.dp)
                )
                Image(
                    painter = painterResource(id = item.category.getIcon()),
                    contentDescription = stringResource(R.string.txt_item_category),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 10.dp)
                )
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        text = item.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.description,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = stringResource(
                            R.string.txt_dollar_sign,
                            item.estimatedPrice),
                        fontSize = 12.sp,
                    )
                }
                Spacer(modifier = Modifier.fillMaxSize(0.3f))
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.btn_edit),
                    modifier = Modifier
                        .clickable { onItemEdit(item) }
                        .padding(end = 5.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = stringResource(R.string.btn_delete),
                    modifier = Modifier
                        .clickable { onItemDelete(item) }
                        .padding(end = 2.dp)
                )
            }
        }
    }
}