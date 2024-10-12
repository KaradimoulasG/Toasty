# Toasty

[![](https://jitpack.io/v/KaradimoulasG/Toasty.svg)](https://jitpack.io/#KaradimoulasG/Toasty) ![GitHub issues](https://img.shields.io/github/issues/KaradimoulasG/Toasty) ![GitHub forks](https://img.shields.io/github/forks/KaradimoulasG/Toasty) [![GitHub stars](https://img.shields.io/github/stars/KaradimoulasG/Toasty)](https://github.com/KaradimoulasG/Toasty/stargazers)

A light and simple Android library that creates a toast message.
Created using Jetpack Compose for its UI toolkit. Toasty provides two different sliding toasts with animation from the top and bottom of the screen so you no longer have to create them from scratch while also giving you some flexibility on how to customize them.

## How to add in your project
Step 1. In your root `build.gradle` at the end of repositories:
```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency at `app/build.gradle` with following code

```
	dependencies {
	        implementation 'com.github.KaradimoulasG:Toasty:0.0.4'
	}
```


## How to use ?


```kotlin

// Inside your application theme
fun SlidingToast(
    modifier: Modifier = Modifier,
    messageType: MessageType = MessageType.DEFAULT,
    message: String = "An unexpected error occurred. Please try again later",
    duration: Long = 2000,
    height: Dp = 160.dp,
    width: Dp? = null,
    textColor: Color = Color.White,
    fontSize: TextUnit = 16.sp,
    cornerRadius: Dp = 12.dp,
    fromTop: Boolean = true,
    onDismissCallback: @Composable () -> Unit = {},
)

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
