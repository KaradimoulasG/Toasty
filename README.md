# Toasty

[![](https://jitpack.io/v/KaradimoulasG/Toasty.svg)](https://jitpack.io/#KaradimoulasG/Toasty)

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
```groove
    dependencies {
	        implementation 'com.github.KaradimoulasG:toasty:Tag'
    }
```

## How to use ?


```kotlin

        // Inside your application theme
        TopToast(Modifier, MessageType.DEFAULT, "Your message here")

```
