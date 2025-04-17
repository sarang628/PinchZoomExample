# 핀치 줌 이미지 뷰어

<img src="./screenshots/sample.gif" width="200">

## 준비

## 라이브러리 다운

라이브러리를 다운받아 프로젝트에 추가 하기
https://github.com/sarang628/pinchzoom

## 이미지 로드 라이브러리 설정

coil glide와 같은 이미지 라이브러리를 설정

```
implementation("io.coil-kt:coil-compose:2.6.0")
```

## 사용법

### ImageLoader

다양한 이미지 라이브러리를 사용할 수 있도록, 이미지 로더를 받는 방식으로 구현
핀치줌 라이브러리는 제공받은 이미지로더를 사용해 이미지로드 및 줌 기능을 설정.

이미지 로더의 예

```kotlin
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
```

### PinchZoomImageBox

핀치줌 시 화면 밖에 확대된 이미지가 나오도록 해주는 Layout
PinchZoomImageBox 호출 시 pinchZoomableImage, zoomState를 제공하는데 !!pinchZoomableImage!!로 이미지를 로드해야 함 주의.

```kotlin
PinchZoomImageBox(imageLoader = coilAsyncImageLoader())
{ pinchZoomableImage, // 이 이미지 로더를 사용해서 이미지를 로드 해야. 줌 기능과 바깥에 이미지가 확대되는 기능이 적용됨.
  zoomState ->

}
```