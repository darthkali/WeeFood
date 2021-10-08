# WeeFood

<p align="center">
 <img src="https://user-images.githubusercontent.com/46423967/130109598-45d9d370-e34f-43d4-99e4-4af42acd9411.png" height="400" />
</p>

## Basis
<img src="https://cmota.github.io/kmp-codelabs/img/657b1858759b67ee.png"  height="100" /> 
<img src="https://www.logo.wine/a/logo/Kotlin_(programming_language)/Kotlin_(programming_language)-Logo.wine.svg" height="100"/> <br>

## UI
<img src="https://tabris.com/wp-content/uploads/2021/06/jetpack-compose-icon_RGB.png"  height="100" /> 
<img src="https://img.icons8.com/color/50/000000/swiftui.png" height="100" /><br>


## Dependendy Injection
<img src="https://avatars.githubusercontent.com/u/38280958?s=200&v=4"  height="100" /> <br>

## Database / Remote
<img src="https://cdn.pixabay.com/photo/2013/09/18/12/13/sqlite-183454_1280.png"  height="100" /> 
<img src="https://repository-images.githubusercontent.com/40136600/f3f5fd00-c59e-11e9-8284-cb297d193133"  height="100" /> <br>

## Tests
All test are shared between iOS and Android. In the `.run` directory are 2 configurations. These configurations run all Test (separate for iOS and Android) at once.

##Run iOS App
To run the iOS app you need to foloww these Steps:
 1. rebuild the app in Android Studio [Taskbar in AS -> Build -> Rebuild Project]
 2. After that you need Xcode. In the App Folder is a `iosWeeFood` Folder. From there you can open the workspace-file in xcode.
 3. run the App with the Start Button on top

 In Case you never run a app in XCode there might be some issues. For some of them i have found a solution.

 At first: Some Solutions must run twice. And sometimes you need to rebuild the app in Android Studio again.

 #No Gradle Permission
 1. open the Terminal in root directory from the app
 2. run: `chmod +x gradlew` to give full permission

 #shared or other modules are missing
 1. open the Terminal in `iosWeeFood` directory from the app
 2. run: `pod deintegrate; pod install` to clean the pod  and reinstall it

 #SQL-Delight problems
 1. go in xcode to the root project
 2. Build Settings -> Linking -> Other Linker Flag
 3. Set a new variable `-lsqlite3`

 #no command line tool set
 1. go in xcode to settings -> locations
 2. set a xcode version in the command line tool sections





- Android Studio Arctic Fox 2020.3.1
- Xcode 12.5.1
- Koin
- Jetpack Compose
- Kotlin Multiplatform Mobile
- Swift UI

**Install Pod Files**


install gradle via terminal
run: `brew install gradle`

run gradle and gradle wrapper in the main directory of the app
run: `gradle; gradle wrapper`

go into the iosWeeFood Folder and open the Terminal
run: `pod deintegrate; pod install`



