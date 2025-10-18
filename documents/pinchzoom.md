핀치 줌 기능을 분석

목표: 기존 구현 코드 로직을 이해

## 이미지에 손가락을 갖다 댔을 때 발생하는 이벤트 원리.

AwaitPointerEventScope에서 현재 포인터에 대한 이벤트를 받을 수 있다.

아래 현재 포인터 이벤트 변수를 볼 수 있다.(val currentEvent : PointerEvent)

```
interface AwaitPointerEventScope : Density {
    val             size                    : IntSize
    val             extendedTouchPadding    : Size get()        = Size.Zero
    val             currentEvent            : PointerEvent
    val             viewConfiguration       : ViewConfiguration
    suspend fun     awaitPointerEvent()     : PointerEvent
    suspend fun <T> withTimeoutOrNull()     : T?                = block()
    suspend fun <T> withTimeout()           : T                 = block()
}
```

그러면 어떻게 AwaitPointerEventScope를 이미지에 설정 할 수 있을까?

Modifier.pointerInput()에서 이 기능을 지원한다.

Modifier.pointerInput() 사용하면 우선 PointerInputScope를 사용 할 수 있는데,

이 PointerInputScope 안에서 AwaitPointerEventScope를 만들 수가 있다.


## 줌 처리

줌을 처리하기 위해서는 PointerEvent 가 필요하다.

위에 설명한 AwaitPointerEventScope 안에서 현재 손가락으로 포인터 중인 PointerEvent에 대해

얻을 수 있다.

위에 설명을 보면 PointerEvent를 얻는 과정을 알 수 있다.

PointerEvent에서 zoom을 계산해주는 확장 함수를 제공한다.

```
fun PointerEvent.calculateZoom(): Float
```

위 함수에서 return 해주는 Float을 누적으로 곱해주면 된다.

## 줌 최대 최소 범위 정하기.

coerce : 강제하다.

coerceIn 강제로 안으로 넣겠다는 의미이다.

```
Float.coerceIn(max, min)
```

위 함수를 사용하면 최대 최소값을 쉽게 설정할 수 있다.

## 줌 뿐만 아니라 손가락을 이동하면 같이 이미지도 같이 이동 시키기

```
fun PointerEvent.calculatePan(): Offset
```

이전 포인터와 현재 포인터의 차이 값을 return 해주는 확장 함수이다.

이 값을 누적으로 더해줘 현재 손가락으로 얼마나 움직였는지 알 수 있다.


---------------------------------------------------------------------------------

Modifier.pointerInput(PointerInputScope) : 포인팅 이벤트를 받을 수 있는 기능.

- PointerInputScope.awaitPointerEventScope 호출
- AwaitPointerEventScope.awaitPointerEvent 가 포인트 이벤트를 받을 수 있게 해줌.
- PointerInputScope or AwaitPointerEventScope 확장한 gestureEvent를 사용 할 수 있음.
- 다른 키가 들어오면 진행중인 이벤트가 취소됨.


PointerInputScope.awaitPointerEventScope
- block을 설치 한다.
- 이 block에서 input event를 받을 수 있다.

AwaitPointerEventScope.awaitPointerEvent
- PointerEvent를 report 해준다.

PointerEventPass
- Initial, Main, Final 되어있음
- 포인터 입력에 대한 이벤트
- Initial: Down the tree from ancestor to descendant.
- Main: Up the tree from descendant to ancestor.
- Final: Down the tree from ancestor to descendant.


다시 돌아와 Modifier.pointerInput를 사용하면 PointerInputScope를 받을 수 있다.

PointerInputScope 를 사용할 수 있다면 
이 인터페이스를 확장한 함수인 awaitEachGesture를 사용할 수 있다.



Modifier을 적용한 컴포넌트에 pointer 입력을 처리할 수 있음.

Modifier.pointerInput 안에 파라미터로 PointerInputScope 가  

AwaitPointerEventScope

PointerInputScope : 


PointerInputScope_awaitPointerEventScope

point 입력 block를 설치한다.

point 입력을 기다리고 입력에 대한 응답을 보내줌.

다중으로 만드는게 가능하다는 것 같음.