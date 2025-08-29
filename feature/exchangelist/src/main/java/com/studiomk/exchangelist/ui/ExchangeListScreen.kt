package com.studiomk.exchangelist.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExchangeListScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Hello Test!!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ExchangeListScreenPreview() {
    ExchangeListScreen()
}