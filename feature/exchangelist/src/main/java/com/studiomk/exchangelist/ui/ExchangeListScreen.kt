package com.studiomk.exchangelist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.exchangelist.R
import com.studiomk.exchangelist.viewmodel.ExchangeListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeListScreen(
    modifier: Modifier = Modifier,
    viewModel: ExchangeListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val exchangeList = uiState.exchangeList

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(exchangeList, key = { it.id }) { item ->
            ExchangeCard(item)
        }
    }
}

@Composable
fun ExchangeCard(
    exchangeUi: ExchangeUi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = stringResource(R.string.exchange_name, exchangeUi.name),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(R.string.exchange_logo, exchangeUi.logo),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(R.string.exchange_spot_volume_usd, exchangeUi.spotVolumeSd),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(R.string.exchange_date, exchangeUi.dateLaunched),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExchangeListScreenPreview() {
    ExchangeListScreen()
}