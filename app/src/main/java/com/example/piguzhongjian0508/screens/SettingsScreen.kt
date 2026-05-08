package com.example.piguzhongjian0508.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.piguzhongjian0508.ui.components.SoftHeader
import com.example.piguzhongjian0508.ui.components.SoftListItem
import com.example.piguzhongjian0508.ui.theme.SoftMuted

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onOpenPrivacySettings: () -> Unit,
) {
    val items = listOf(
        "账号与安全",
        "隐私设置",
        "消息设置",
        "显示设置",
        "缓存清理",
        "语言",
        "关于版本",
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SoftHeader(title = "设置", onBack = onBack)
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            items(items) { item ->
                SettingsListItem(
                    title = item,
                    onClick = if (item == "隐私设置") onOpenPrivacySettings else null,
                )
            }
        }
    }
}

@Composable
private fun SettingsListItem(
    title: String,
    onClick: (() -> Unit)?,
) {
    SoftListItem(
        title = title,
        onClick = onClick,
        trailingContent = {
            Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = SoftMuted)
        },
    )
}
