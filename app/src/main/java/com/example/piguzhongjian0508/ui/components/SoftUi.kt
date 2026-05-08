package com.example.piguzhongjian0508.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.piguzhongjian0508.ui.theme.SoftAccent
import com.example.piguzhongjian0508.ui.theme.SoftBackground
import com.example.piguzhongjian0508.ui.theme.SoftDarkShadow
import com.example.piguzhongjian0508.ui.theme.SoftForeground
import com.example.piguzhongjian0508.ui.theme.SoftLightShadow
import com.example.piguzhongjian0508.ui.theme.SoftMuted

private val DefaultSoftShape = RoundedCornerShape(28.dp)

fun Modifier.softRaised(
    cornerRadius: Dp = 28.dp,
    shadowOffset: Dp = 7.dp,
    shadowBlur: Dp = 18.dp,
): Modifier = shadow(
    elevation = (shadowOffset.value + shadowBlur.value * 0.25f).dp,
    shape = RoundedCornerShape(cornerRadius),
    clip = false,
    ambientColor = SoftLightShadow,
    spotColor = SoftDarkShadow,
)

fun Modifier.softInset(
    cornerRadius: Dp = 28.dp,
    strokeWidth: Dp = 2.dp,
): Modifier = drawBehind {
    val radius = cornerRadius.toPx()
    val stroke = strokeWidth.toPx()
    drawRoundRect(
        color = SoftDarkShadow.copy(alpha = 0.22f),
        topLeft = androidx.compose.ui.geometry.Offset(stroke, stroke),
        size = androidx.compose.ui.geometry.Size(size.width - stroke * 2, size.height - stroke * 2),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(radius, radius),
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = stroke),
    )
    drawRoundRect(
        color = SoftLightShadow.copy(alpha = 0.95f),
        topLeft = androidx.compose.ui.geometry.Offset(-stroke / 2, -stroke / 2),
        size = androidx.compose.ui.geometry.Size(size.width - stroke, size.height - stroke),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(radius, radius),
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = stroke),
    )
}

@Composable
fun SoftSurface(
    modifier: Modifier = Modifier,
    shape: Shape = DefaultSoftShape,
    cornerRadius: Dp = 28.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .softRaised(cornerRadius = cornerRadius)
            .clip(shape)
            .background(SoftBackground, shape)
            .padding(contentPadding),
    ) {
        content()
    }
}

@Composable
fun SoftInsetSurface(
    modifier: Modifier = Modifier,
    shape: Shape = DefaultSoftShape,
    cornerRadius: Dp = 28.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(SoftBackground, shape)
            .softInset(cornerRadius = cornerRadius)
            .padding(contentPadding),
    ) {
        content()
    }
}

@Composable
fun SoftHeader(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(SoftBackground)
            .padding(horizontal = 20.dp)
            .padding(top = 12.dp, bottom = 14.dp)
            .height(72.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (onBack != null) {
            SoftIconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = onBack,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回",
                    tint = SoftForeground,
                )
            }
        }

        SoftInsetSurface(
            modifier = Modifier
                .height(50.dp)
                .widthIn(min = 168.dp, max = 220.dp),
            shape = RoundedCornerShape(25.dp),
            cornerRadius = 25.dp,
            contentPadding = PaddingValues(horizontal = 24.dp),
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = title,
                    color = SoftForeground,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
fun SoftIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .softRaised(cornerRadius = 16.dp, shadowOffset = 4.dp, shadowBlur = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SoftBackground)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Composable
fun SoftListItem(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    val shape = RoundedCornerShape(22.dp)
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    SoftSurface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .then(clickableModifier),
        shape = shape,
        cornerRadius = 22.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f),
                color = SoftForeground,
                style = MaterialTheme.typography.bodyLarge,
            )
            trailingContent?.invoke()
        }
    }
}

@Composable
fun SoftBottomTabItem(
    selected: Boolean,
    title: String,
    onClick: () -> Unit,
    icon: @Composable (Color) -> Unit,
    modifier: Modifier = Modifier,
) {
    val tint = if (selected) SoftAccent else SoftMuted
    val shape = RoundedCornerShape(24.dp)
    val itemModifier = modifier
        .fillMaxHeight()
        .clip(shape)
        .clickable(onClick = onClick)

    if (selected) {
        SoftInsetSurface(
            modifier = itemModifier,
            shape = shape,
            cornerRadius = 24.dp,
            contentPadding = PaddingValues(horizontal = 10.dp),
        ) {
            BottomTabContent(title = title, tint = tint, icon = icon)
        }
    } else {
        Box(modifier = itemModifier.padding(horizontal = 10.dp)) {
            BottomTabContent(title = title, tint = tint, icon = icon)
        }
    }
}

@Composable
private fun BottomTabContent(
    title: String,
    tint: Color,
    icon: @Composable (Color) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        icon(tint)
        Spacer(Modifier.width(8.dp))
        Text(
            text = title,
            color = tint,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
        )
    }
}
