# Toasty

[![](https://jitpack.io/v/KaradimoulasG/Toasty.svg)](https://jitpack.io/#KaradimoulasG/Toasty) ![GitHub issues](https://img.shields.io/github/issues/KaradimoulasG/Toasty) ![GitHub forks](https://img.shields.io/github/forks/KaradimoulasG/Toasty) [![GitHub stars](https://img.shields.io/github/stars/KaradimoulasG/Toasty)](https://github.com/KaradimoulasG/Toasty/stargazers)

A light and simple Android library that creates a toast message.
Created using Jetpack Compose for its UI toolkit. Toasty provides two different sliding toasts with animation from the top and bottom of the screen so you no longer have to create them from scratch while also giving you some flexibility on how to customize them.

## How to add in your project
In the `build.gradle` add maven central repository:
```
repositories {
    maven { url 'https://jitpack.io' }
}
```

If you are using the Kotlin DSL:

```
repositories {
    maven { url = uri("https://www.jitpack.io" ) }
}
```

Then, add library at `app/build.gradle` with following code
```groovy
dependencies {
    implementation 'com.github.KaradimoulasG:toasty:Tag'
}
```

```Kotlin
dependencies {
    implementation("com.github.KaradimoulasG:Toasty:Tag")
}
```


## How to use ?


```kotlin

// Inside your application theme
TopToast(
    modifier: Modifier = Modifier,
    messageType: MessageType = MessageType.DEFAULT,
    message: String = "An unexpected error occurred. Please try again later",
    height: Dp = 160.dp,
    width: Dp? = null,
    onDismissCallback: @Composable () -> Unit = {}
)

// Similarly with the BottomToast(...) component

```

## Preview
 
See how it looks like in action

![](https://github.com/KaradimoulasG/Toasty/blob/master/screenshots/usage.gif)

## License
```
MIT License

Copyright (c) 2023 George Karadimoulas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.MIT License
```
