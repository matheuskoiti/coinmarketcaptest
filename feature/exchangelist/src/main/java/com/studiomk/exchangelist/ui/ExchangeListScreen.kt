package com.studiomk.exchangelist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.exchangelist.R
import com.studiomk.exchangelist.viewmodel.ExchangeListViewModel
import com.studiomk.exchangelist.viewmodel.ExchangeState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeListScreen(
    modifier: Modifier = Modifier,
    viewModel: ExchangeListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        ExchangeState.ExchangeLoading -> {
            LoadingScreen()
        }
        is ExchangeState.ExchangeLoadedState -> {
            ExchangeList(uiState, modifier)
        }
        is ExchangeState.ExchangeLoadError -> {
            ExchangeError(
                errorMessage = (uiState as ExchangeState.ExchangeLoadError).errorMessage,
                onTryAgainClick = { viewModel.getExchangeList() }
            )
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.exchange_list_loading),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ExchangeError(
    errorMessage: String,
    onTryAgainClick: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(
                    R.string.exchange_list_error,
                    errorMessage
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onTryAgainClick.invoke() }
            ) {
                Text(
                    text = stringResource(R.string.exchange_list_error_try_again),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }
}

@Composable
private fun ExchangeList(
    uiState: ExchangeState,
    modifier: Modifier
) {
    val exchangeList = (uiState as ExchangeState.ExchangeLoadedState).exchangeList
    LazyColumn(
        modifier = modifier
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
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(exchangeUi.logo)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.exchange_logo),
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_broken_image),
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = exchangeUi.name,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = stringResource(R.string.exchange_spot_volume_usd, exchangeUi.spotVolumeUsd),
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