package com.example.piguzhongjian0508.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onOpenSettings: () -> Unit) {
    val items = listOf(
        "我的资料",
        "消息通知",
        "健康报告",
        "设备管理",
        "会员中心",
        "设置",
        "关于我们",
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("个人中心") })
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            items(items) { item ->
                ProfileListItem(
                    title = item,
                    isSettings = item == "设置",
                    onClick = if (item == "设置") onOpenSettings else null,
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun ProfileListItem(
    title: String,
    isSettings: Boolean,
    onClick: (() -> Unit)?,
) {
    ListItem(
        modifier = if (onClick != null) {
            Modifier.clickable(onClick = onClick)
        } else {
            Modifier
        },
        headlineContent = { Text(title) },
        leadingContent = if (isSettings) {
            { Icon(Icons.Filled.Settings, contentDescription = null) }
        } else {
            null
        },
        trailingContent = {
            Icon(Icons.Filled.ChevronRight, contentDescription = null)
        },
    )
}
