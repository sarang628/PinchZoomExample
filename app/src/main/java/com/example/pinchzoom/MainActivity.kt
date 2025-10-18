package com.example.pinchzoom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pinchzoom.ui.theme.PinchZoomTheme
import com.sarang.torang.di.pinchzoom.PinchZoomImageBoxSample

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PinchZoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        PinchZoomImageBoxSample(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}