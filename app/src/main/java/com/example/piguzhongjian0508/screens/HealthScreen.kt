package com.example.piguzhongjian0508.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.piguzhongjian0508.R
import com.example.piguzhongjian0508.state.ButtCrackViewModel
import com.example.piguzhongjian0508.ui.components.SoftHeader
import com.example.piguzhongjian0508.ui.components.SoftSurface
import com.example.piguzhongjian0508.ui.theme.SoftAccent
import com.example.piguzhongjian0508.ui.theme.SoftForeground
import com.example.piguzhongjian0508.ui.theme.SoftWarning
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthScreen(viewModel: ButtCrackViewModel) {
    val isButtCrackClosed by viewModel.isButtCrackClosed.collectAsState()
    var shouldShowBearImages by remember { mutableStateOf(false) }

    // 避免首屏同步解码 PNG 导致 API 37 模拟器长时间停在系统 Splash/黑屏。
    LaunchedEffect(Unit) {
        withFrameNanos { }
        delay(120)
        shouldShowBearImages = true
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SoftHeader(title = "健康状况", height = 52.dp, slotHeight = 50.dp)
        },
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 6.dp),
        ) {
            val gap = 8.dp
            val statusHeight = 72.dp
            val metricsHeight = 70.dp
            val adviceHeight = 90.dp
            val reservedHeight = statusHeight + metricsHeight + adviceHeight + gap * 3
            val bearPanelHeight = (maxHeight - reservedHeight).coerceIn(220.dp, 330.dp)
            val bearImageHeight = (bearPanelHeight - 38.dp).coerceAtLeast(176.dp)

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(gap),
            ) {
                HealthStatusMessage(
                    isAbnormal = isButtCrackClosed,
                    modifier = Modifier.height(statusHeight),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(bearPanelHeight),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    BearImagePanel(
                        title = "背面",
                        drawableRes = if (isButtCrackClosed) {
                            R.drawable.bear_back_alert
                        } else {
                            R.drawable.bear_back
                        },
                        showImage = shouldShowBearImages,
                        imageHeight = bearImageHeight,
                        modifier = Modifier.weight(1f),
                    )
                    BearImagePanel(
                        title = "正面",
                        drawableRes = R.drawable.bear_front,
                        showImage = shouldShowBearImages,
                        imageHeight = bearImageHeight,
                        imageModifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(
                                scaleX = 1.11f,
                                scaleY = 1.30f,
                            ),
                        modifier = Modifier.weight(1f),
                    )
                }

                HealthMetricsSection(
                    metricsHeight = metricsHeight,
                    adviceHeight = adviceHeight,
                )
            }
        }
    }
}

@Composable
private fun HealthStatusMessage(
    isAbnormal: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        if (isAbnormal) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "重大风险：",
                    color = SoftWarning,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "您的屁股中间出现了一条缝",
                    color = SoftWarning,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            Text(
                text = "暂无重大风险",
                color = Color(0xFF2E7D32),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun HealthMetricsSection(
    metricsHeight: androidx.compose.ui.unit.Dp,
    adviceHeight: androidx.compose.ui.unit.Dp,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(metricsHeight),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            HealthMetricCard(
                title = "体重",
                value = "250kg  二百五",
                status = "正常",
                statusColor = Color(0xFF2E7D32),
                icon = {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "正常",
                        tint = Color(0xFF2E7D32),
                        modifier = Modifier.size(22.dp),
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
            )
            HealthMetricCard(
                title = "体脂率",
                value = "77.8% 吃吃吧",
                status = "偏高",
                statusColor = SoftWarning,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowUpward,
                        contentDescription = "偏高",
                        tint = SoftWarning,
                        modifier = Modifier.size(22.dp),
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
            )
        }

        SoftSurface(
            modifier = Modifier
                .fillMaxWidth()
                .height(adviceHeight),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    HealthInfoText(
                        text = "评语&近期建议",
                    )
                    Icon(
                        imageVector = Icons.Filled.MedicalServices,
                        contentDescription = "医生建议",
                        tint = SoftAccent,
                        modifier = Modifier.size(22.dp),
                    )
                }
                HealthInfoText(
                    text = "建议您发布低脂作品，以降低体脂率。",
                )
            }
        }
    }
}

@Composable
private fun HealthMetricCard(
    title: String,
    value: String,
    status: String,
    statusColor: Color,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    SoftSurface(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                HealthInfoText(
                    text = title,
                )
                icon()
                Text(
                    text = status,
                    color = statusColor,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                HealthInfoText(
                    text = value,
                )
            }
        }
    }
}

@Composable
private fun HealthInfoText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        color = SoftForeground,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
private fun BearImagePanel(
    title: String,
    drawableRes: Int,
    showImage: Boolean,
    imageHeight: androidx.compose.ui.unit.Dp,
    imageModifier: Modifier = Modifier.fillMaxSize(),
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier = Modifier,
) {
    SoftSurface(
        modifier = modifier.fillMaxHeight(),
        contentPadding = PaddingValues(8.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            Text(
                text = title,
                color = SoftForeground,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight),
                contentAlignment = Alignment.Center,
            ) {
                if (showImage) {
                    Image(
                        painter = painterResource(drawableRes),
                        contentDescription = "熊${title}图",
                        modifier = imageModifier,
                        contentScale = contentScale,
                    )
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
