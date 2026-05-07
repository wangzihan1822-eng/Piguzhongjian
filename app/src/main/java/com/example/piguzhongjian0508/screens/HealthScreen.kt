package com.example.piguzhongjian0508.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.piguzhongjian0508.R
import com.example.piguzhongjian0508.state.ButtCrackViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen(viewModel: ButtCrackViewModel) {
    val isButtCrackClosed by viewModel.isButtCrackClosed.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("健康状况") })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            if (!isButtCrackClosed) {
                AbnormalWarning()
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                BearImagePanel(
                    title = "正面",
                    drawableRes = R.drawable.bear_front,
                    modifier = Modifier.weight(1f),
                )
                BearImagePanel(
                    title = "背面",
                    drawableRes = if (isButtCrackClosed) {
                        R.drawable.bear_back
                    } else {
                        R.drawable.bear_back_alert
                    },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun AbnormalWarning() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "异常：",
            color = Color.Red,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "您的屁股中间出现了一条缝",
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun BearImagePanel(
    title: String,
    drawableRes: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.heightIn(min = 460.dp),
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp,
        color = MaterialTheme.colorScheme.surfaceContainer,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Image(
                painter = painterResource(drawableRes),
                contentDescription = "熊${title}图",
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(maxHeight = 620.dp),
                contentScale = ContentScale.Fit,
            )
        }
    }
}
