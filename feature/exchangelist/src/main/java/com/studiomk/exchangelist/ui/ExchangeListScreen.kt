package com.studiomk.exchangelist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.studiomk.exchangelist.R
import com.studiomk.exchangelist.viewmodel.ExchangeListViewModel
import com.studiomk.exchangelist.viewmodel.ExchangeState
import com.studiomk.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeListScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ExchangeListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when(uiState) {
        ExchangeState.ExchangeLoading -> {
            LoadingScreen()
        }
        is ExchangeState.ExchangeLoadedState -> {
            ExchangeList(uiState, modifier, navController)
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
private fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
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
    modifier: Modifier = Modifier,
    errorMessage: String,
    onTryAgainClick: () -> Unit
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
    modifier: Modifier,
    navController: NavHostController,
) {
    val exchangeList = (uiState as ExchangeState.ExchangeLoadedState).exchangeList
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(exchangeList, key = { it.id }) { item ->
            ExchangeCard(
                exchangeUi = item,
                modifier = modifier,
                onCardClick = {
                    navController.navigate("${Screen.EXCHANGE_DETAIL}/${item.id}")
                }
            )
        }
    }
}