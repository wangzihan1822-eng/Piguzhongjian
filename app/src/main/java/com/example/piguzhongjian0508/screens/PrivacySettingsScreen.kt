package com.example.piguzhongjian0508.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.example.piguzhongjian0508.state.ButtCrackViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacySettingsScreen(
    viewModel: ButtCrackViewModel,
    onBack: () -> Unit,
) {
    val isButtCrackClosed by viewModel.isButtCrackClosed.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("隐私设置") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            item {
                ListItem(
                    modifier = Modifier.toggleable(
                        value = isButtCrackClosed,
                        role = Role.Switch,
                        onValueChange = viewModel::setButtCrackClosed,
                    ),
                    headlineContent = { Text("关闭屁股缝") },
                    supportingContent = { Text("开启后健康页隐藏异常提示") },
                    trailingContent = {
                        Switch(
                            checked = isButtCrackClosed,
                            onCheckedChange = null,
                        )
                    },
                )
                HorizontalDivider()
            }
            item { PlainPrivacyItem("数据权限") }
            item { PlainPrivacyItem("个性化推荐") }
            item { PlainPrivacyItem("黑名单") }
            item { PlainPrivacyItem("授权管理") }
            item { PlainPrivacyItem("隐私政策") }
            item { PlainPrivacyItem("账号注销") }
        }
    }
}

@Composable
private fun PlainPrivacyItem(title: String) {
    ListItem(headlineContent = { Text(title) })
    HorizontalDivider()
}
