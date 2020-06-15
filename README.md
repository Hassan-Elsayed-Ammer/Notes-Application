# Note-Application 

Android Application To save data in local Storage


## Description

user can save his own data or note in a good User interface
with title and paragraph in his note he can edit and delete his note Also 


##Technology used
we try to make simple demo As an application on Jetpack compose in AndroidX  

##By using
#Kotlin
#Room database
#Kotlin Coroutine
#Navigation

##Libraries

```bash
apply plugin: "androidx.navigation.safeargs"
apply plugin: "kotlin-kapt"

def room_version = "2.2.5"
def nav_version = "2.3.0-rc01"

//Material Design
implementation 'com.google.android.material:material:1.1.0'

//room
implementation "androidx.room:room-runtime:$room_version"
kapt "androidx.room:room-compiler:$room_version"

//Nav Fragment
implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

//coroutines And coroutines for room
implementation "androidx.room:room-ktx:$room_version"
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4'
```
