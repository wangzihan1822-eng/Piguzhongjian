package com.example.piguzhongjian0508

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.piguzhongjian0508.navigation.PiguzhongjianApp
import com.example.piguzhongjian0508.ui.theme.Piguzhongjian0508Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Piguzhongjian0508Theme {
                PiguzhongjianApp()
            }
        }
    }
}
