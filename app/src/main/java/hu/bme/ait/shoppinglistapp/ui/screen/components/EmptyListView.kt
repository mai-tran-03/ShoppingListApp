package hu.bme.ait.shoppinglistapp.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.ui.theme.PurpleGrey80

@Composable
fun EmptyListView() {
    Column(
        modifier = Modifier
            .background(color = PurpleGrey80)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.txt_no_items))
    }
}