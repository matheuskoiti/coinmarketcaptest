package com.studiomk.exchangedetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExchangeDetailScreen(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.padding(16.dp).fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "ID: 123345",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = "Logo",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Name",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Description: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Urls: www.teste.com",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Taker fee",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Maker fee",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Date launched: 12/12/1990",
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ExchangeDetailScreenPreview() {
    ExchangeDetailScreen()
}