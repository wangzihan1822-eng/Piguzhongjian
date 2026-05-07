package com.example.piguzhongjian0508.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ButtCrackViewModel : ViewModel() {
    private val _isButtCrackClosed = MutableStateFlow(false)

    // 全局屁股缝状态：false 表示异常打开，true 表示已关闭。
    val isButtCrackClosed: StateFlow<Boolean> = _isButtCrackClosed.asStateFlow()

    fun setButtCrackClosed(closed: Boolean) {
        _isButtCrackClosed.value = closed
    }
}
