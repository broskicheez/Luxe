package com.luxe.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.luxe.app.ui.screens.MenuScreen
import com.luxe.app.ui.theme.LuxeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LuxeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MenuScreen()
                }
            }
        }
    }
}