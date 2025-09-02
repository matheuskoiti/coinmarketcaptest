package com.studiomk.exchangedetail.ui

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.studiomk.domain.model.Currency
import com.studiomk.domain.model.ExchangeUi
import com.studiomk.exchangedetail.R
import com.studiomk.exchangedetail.viewmodel.ExchangeDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangeDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ExchangeDetailViewModel = koinViewModel(),
    exchangeId: String
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(exchangeId) {
        viewModel.loadDetails(exchangeId)
    }

    when(uiState){
        is ExchangeDetailState.ExchangeDetailLoadedState -> {
            ExchangeDetailInfo(
                modifier = modifier,
                exchangeUi = (uiState as ExchangeDetailState.ExchangeDetailLoadedState).assets.exchangeUi,
                currencyList = (uiState as ExchangeDetailState.ExchangeDetailLoadedState).assets.currencyList,
                navController = navController
            )
        }
        is ExchangeDetailState.ExchangeLoadError -> {
            ExchangeError(
                errorMessage = (uiState as ExchangeDetailState.ExchangeLoadError).errorMessage,
                onTryAgainClick = { viewModel.loadDetails(exchangeId) }
            )
        }
        ExchangeDetailState.ExchangeLoading -> {
            LoadingScreen()
        }
    }
}

@Composable
private fun ExchangeDetailInfo(
    modifier: Modifier,
    exchangeUi: ExchangeUi,
    currencyList: List<Currency>,
    navController: NavHostController
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
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
                Text(
                    text = "ID: ${exchangeUi.id}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
            if (exchangeUi.description.isNotEmpty()) {
                Surface(
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shadowElevation = 4.dp,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = exchangeUi.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.exchange_urls, exchangeUi.url),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.exchange_taker_fee, exchangeUi.takerFee),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.exchange_maker_fee, exchangeUi.makerFee),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.exchange_date, exchangeUi.dateLaunched),
                style = MaterialTheme.typography.bodyLarge
            )

            if (currencyList.isNotEmpty()) {
                CurrencyListCard(modifier, currencyList)
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { navController.popBackStack() }
            ) {
                Text(text = stringResource(R.string.exchange_detail_back))
            }
        }
    }
}

@Composable
private fun CurrencyListCard(
    modifier: Modifier,
    currencyList: List<Currency>
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val displayedList = if (expanded) currencyList else currencyList.take(5)

    Spacer(modifier = Modifier.height(24.dp))
    Surface(
        modifier = modifier.padding(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.exchange_detail_asset_list),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            displayedList.forEach { currency ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = currency.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = currency.priceUsd,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            if (currencyList.size > 5) {
                TextButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = if (expanded) stringResource(R.string.exchange_detail_see_less) else stringResource(R.string.exchange_detail_see_more),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
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
                text = stringResource(R.string.exchange_details_loading),
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
                text = errorMessage
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


@Preview(showBackground = true)
@Composable
fun ExchangeDetailScreenPreview() {
    ExchangeDetailScreen(exchangeId = "12222", navController = rememberNavController())
}