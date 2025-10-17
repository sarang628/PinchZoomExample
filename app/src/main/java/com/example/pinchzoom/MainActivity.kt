package com.example.pinchzoom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pinchzoom.submodule.pinchzoom.PinchZoomImageLoader
import com.example.pinchzoom.ui.theme.PinchZoomTheme
import com.sarang.torang.di.pinchzoom.PinchZoomImageBoxSample
import com.sarang.torang.di.pinchzoom.PinchZoomState
import com.sarang.torang.di.pinchzoom.pinchZoomAndTransform

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