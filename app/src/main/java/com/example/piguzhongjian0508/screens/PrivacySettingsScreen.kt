package com.example.piguzhongjian0508.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.piguzhongjian0508.state.ButtCrackViewModel
import com.example.piguzhongjian0508.ui.components.SoftHeader
import com.example.piguzhongjian0508.ui.components.SoftListItem
import com.example.piguzhongjian0508.ui.theme.SoftForeground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacySettingsScreen(
    viewModel: ButtCrackViewModel,
    onBack: () -> Unit,
) {
    val isButtCrackClosed by viewModel.isButtCrackClosed.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SoftHeader(title = "隐私设置", onBack = onBack)
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                SoftListItem(
                    title = "屁股缝状态",
                    modifier = Modifier.toggleable(
                        value = isButtCrackClosed,
                        role = Role.Switch,
                        onValueChange = viewModel::setButtCrackClosed,
                    ),
                    trailingContent = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = if (isButtCrackClosed) "已开启" else "已关闭",
                                color = SoftForeground,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Switch(
                                checked = isButtCrackClosed,
                                onCheckedChange = null,
                            )
                        }
                    },
                )
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
    SoftListItem(title = title)
}
