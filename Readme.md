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

## 구현 idea

- PinchZoomBox 컴포넌트를 만든다.
  - 화면 밖으로 어두운 배경에 줌한 이미지가 나오는 기능을 제공.
  - 이미지 로더는 원하는 라이브러리를 사용할 수 있도록 하기
  - zoom 상태값과 url만 flow 데이터로 받아 연동 할 수 있게 하기.
  - Box안에 컨텐츠를 넣을 수 있도록 하기

- [핀치 줌 기능 구현하기](/documents/pinchzoom.md)

## 사용법 (인스타그램 st 핀치 줌)

```
@Composable
fun PinchZoomImageBoxSample(modifier : Modifier = Modifier){

    val imageUrls = listOf(
        "http://sarang628.iptime.org:89/restaurant_images/278/2025-10-12/07_53_33_425.jpg%3ftype=w800",
        "http://sarang628.iptime.org:89/restaurant_images/245/2025-10-12/01_18_37_646.jpg",
        "http://sarang628.iptime.org:89/restaurant_images/244/2025-08-23/11_46_30_054.jpg",
        "http://sarang628.iptime.org:89/restaurant_images/242/2025-05-03/02_34_45_987.jpeg",
        "http://sarang628.iptime.org:89/restaurant_images/241/2025-05-03/02_32_41_199.jpeg",
        "http://sarang628.iptime.org:89/restaurant_images/239/2025-05-03/02_30_21_802.jpg%3fw=500&h=500&org_if_sml=1",
        "http://sarang628.iptime.org:89/restaurant_images/237/2025-05-03/10_54_53_555.jpg",
        "http://sarang628.iptime.org:89/restaurant_images/236/2025-05-03/09_33_55_764.jpg"
    )

    // Data shared between a zoomed image and the rest of the list when zooming.
    var zoomState by remember { mutableStateOf<PinchZoomState?>(null) }

    Log.d("__PinchZoomImageBoxSample", "recomposition")

    PinchZoomImageBox(
        modifier        = modifier,
        activeZoomState = zoomState,
        imageLoader     = imageLoader
    ){  
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            userScrollEnabled   = remember(zoomState) { zoomState == null } // scrollEnabled는 derivedStateOf로 wrapping → recomposition 방지
        ) {
            items(imageUrls.size) {
                Column {
                    pinchZoomImageLoader(
                        zoomState   = zoomState,
                        onZoomState = { zoomState = it }
                    ).invoke(
                        PunchZoomImageData(
                            model               = imageUrls[it],
                            contentDescription  = null
                        )
                    )
                }
            }
        }
    }
}

// pinch zoom custom image loader
fun pinchZoomImageLoader(
    zoomState: PinchZoomState?,
    onZoomState : (PinchZoomState?)->Unit  ={}
) : PinchZoomImageLoader = @Composable { data ->
    AsyncImage(
        modifier = data.modifier
            .height(200.dp)
            .pinchZoomAndTransform(zoomState, onActiveZoom = { onZoomState(it?.copy(url = data.model)) }),
        model = data.model,
        contentDescription = data.contentDescription
    )
}
```

## 코드 설명

### zoomState
- 핀치 줌을 했을 때 변경되는 데이터
- 바깥 이미지에 데이터를 공유한다.

### pinchZoomImageLoader
- 핀치 줌 기능을 제공하는 이미지 로더

### PinchZoomImageBox
- zoomState, 이미지 로더를 설정하면 바깥에 줌 이미지를 표시해 줌.