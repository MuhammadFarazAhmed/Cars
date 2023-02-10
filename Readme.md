Project Setup:

Android Studio Eel
Android Gradle Plugin Version = 7.4.0
Gradle Version = 7.5

Jetpack Compose UI
MVVM
Clean Architecture (domain with use cases)
Repository
Offline Support
Coroutines with Flow
Koin Dependency injection

Project consists of following module

:app - module for DI
:base - common module for all common and util classes and extensions
:domain - data separation for every module clean code (Use Cases)
:repository - all Repositories and data related classes like Database are placed here for project
:home - every single feature of app is a module like home, profile ,
auth and so on as the features grows each feature is converted into a separate module (Multi modular app)