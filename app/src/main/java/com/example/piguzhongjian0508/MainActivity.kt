package com.example.piguzhongjian0508

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.example.piguzhongjian0508.navigation.PiguzhongjianApp
import com.example.piguzhongjian0508.ui.theme.Piguzhongjian0508Theme
import com.example.piguzhongjian0508.ui.theme.SoftBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = SoftBackground.toArgb()
        window.navigationBarColor = SoftBackground.toArgb()
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
        setContent {
            Piguzhongjian0508Theme {
                PiguzhongjianApp()
            }
        }
    }
}
