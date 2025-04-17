package com.example.pinchzoom

//import coil3.compose.AsyncImage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pinchzoom.ui.theme.PinchZoomTheme
import com.sarang.torang.di.pinchzoom.ImageLoader
import com.sarang.torang.di.pinchzoom.PinchZoomImageBox

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PinchZoomTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PinchZoomExample()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinchZoomExample() {
    val imageSize = 200.dp
    PinchZoomImageBox(imageLoader = coilAsyncImageLoader())
    { pinchZoomableImage, // 이 이미지 로더를 사용해서 이미지를 로드 해야. 줌 기능과 바깥에 이미지가 확대되는 기능이 적용됨.
      zoomState ->
        Box(Modifier.fillMaxSize()) {
            LazyColumn {
                items(10) {
                    Column {
                        Text(
                            text = "Pinch Zoom Image Viewer",
                            fontSize = 30.sp
                        )
                        pinchZoomableImage.invoke(
                            Modifier
                                .height(imageSize),
                            "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhkYTY17vrtw3-dlooIu9n7R7mYFgOwyiCwEtJiFJTuxk4sOCKJ-OVaftSPKX7CfONCn2AMMV70TNP9qfo5avZBaMBn4BGS5DW6wPlbRY2ZZRgBXMEI5HbzduVdwj790uDattXfmQtkE8JJ_OptUUDFpCdJZWKVO_mOuL408H4svVQlt58TcjQe8JWfC5g/s1600/app-quality-performance-play-console-insights-meta.png",
                            ContentScale.Crop,
                            imageSize
                        )
                    }
                }
            }
        }
    }
}


/**
 * 핀치줌 라이브라리 제공용 이미지 로더
 */
fun coilAsyncImageLoader(): ImageLoader {
    return { modifier, url, contentScale ->
        AsyncImage(
            modifier = modifier,
            model = url,
            contentDescription = null,
            contentScale = contentScale ?: ContentScale.Fit
        )
    }
}