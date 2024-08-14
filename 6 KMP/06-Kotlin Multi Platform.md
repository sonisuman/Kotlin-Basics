# 6. Kotlin Multi Platform

<!--suppress CheckImageSize -->
<img src="../images/Logo.png" width="300" alt="">

--

## Overview of Kotlin Multiplatform:

### Introduction to Code Sharing Philosophy

- Kotlin Multiplatform is designed for maximizing code reuse across different platforms (JVM, Android, iOS, JavaScript, and Native). 
- The central design concept is to write business logic and other non-platform-specific code once, and then share that code across multiple platforms without having to duplicate it. 
- This approach not only improves development efficiency but also ensures consistency across different applications.

#### DRY Principle (Don’t Repeat Yourself): 
- Implements the DRY principle by allowing developers to write common code that can be used across multiple platforms. This reduces redundancy and potential for errors, as the same logic is applied consistently across all targets. 
#### Separation of Concerns: 
- Encourages a clear separation between platform-agnostic logic and platform-specific implementations. 
- By using expect/actual declarations, developers can define common interfaces and business logic while allowing for platform-specific implementations where necessary. 
- This separation ensures that the core logic remains untouched when adapting to different platforms.

#### Single Codebase, Multiple Targets: 
- Allows developers to maintain a single codebase for their application’s core functionality, which can be compiled to different targets (JVM, Android, iOS, etc.). This reduces the maintenance burden as changes in the shared code automatically reflect across all platforms.

#### Practical Benefits of Code Sharing

- Consistency Across Platforms: When business logic is shared, it ensures that all platforms exhibit the same behavior, leading to a more uniform user experience and reducing the likelihood of platform-specific bugs.
- Reduced Development Time: By sharing code, development teams can significantly reduce the amount of time spent rewriting the same logic for different platforms. This frees up resources to focus on platform-specific features and optimizations.
- Simplified Testing: Shared code can be tested once in the common module, and those tests can be executed across multiple platforms. This reduces the testing overhead and ensures that the shared logic is reliable across all platforms.

#### KMP Code sharing

- Common Modules: Kotlin Multiplatform projects typically consist of common modules where shared code resides. These modules are agnostic of the target platform and contain the business logic, models, and utility functions that are reused across different platforms.
- Expect/Actual Mechanism: Kotlin’s expect/actual mechanism allows developers to define interfaces or declarations in the common module (using expect), with platform-specific implementations provided in the respective platform modules (using actual). This mechanism provides a way to share logic while still allowing for necessary platform-specific variations.
- Kotlin Multiplatform Libraries: There is a growing ecosystem of Kotlin Multiplatform libraries that facilitate code sharing. These libraries are designed to work seamlessly across different platforms, further simplifying the process of writing cross-platform applications.

#### Challenges and Considerations

- Performance Considerations: While Kotlin Multiplatform provides significant benefits in terms of code reuse, developers need to be aware of potential performance implications when abstracting platform-specific logic.
- Interoperability with Existing Code: Integrating Kotlin Multiplatform into existing projects may require careful planning, especially when dealing with large legacy codebases. Interoperability with existing Java, Swift, and Objective-C code needs to be considered.
- Learning Curve: Although Kotlin is a modern and expressive language, developers may face a learning curve when first adopting Kotlin Multiplatform, particularly in understanding the expect/actual mechanism and managing dependencies across platforms.

---

## Project Structure

[Kotlin Documentation](https://kotlinlang.org/docs/multiplatform-discover-project.html)


[Kotlin Documentation](https://kotlinlang.org/docs/multiplatform-discover-project.html#targets)

[List of Targets](https://kotlinlang.org/docs/multiplatform-dsl-reference.html#targets)

---

## Migrating Android to KMP

Migrating an Android application to a Kotlin Multiplatform (KMP) application involves several steps. The goal is to extract the platform-independent logic from the Android-specific codebase and move it into a shared module that can be used across multiple platforms (e.g., iOS, desktop, web). 

####  Understand Your Current Codebase

- _Audit the Codebase:_ Identify the parts of the Android application that are platform-independent (e.g., business logic, data models, utility functions) and the parts that are Android-specific (e.g., UI components, Android SDK dependencies).
- _Plan the Migration:_ Decide which parts of the code will be shared across platforms and which parts will remain Android-specific.

#### Set Up the Kotlin Multiplatform Project

- Create a New KMP Project
- Update the Gradle Configuration and configure the KMP targets in the `build.gradle.kts` file:

```kotlin
kotlin {
    android()
    ios() // or iosX64(), iosArm64(), iosSimulatorArm64()
    jvm() // for desktop
    js() // for web
    sourceSets {
        val commonMain by getting
        val androidMain by getting
        val iosMain by getting
        // Additional configurations if needed
    }
}

```

#### Create a Shared Module
- Create a shared module (if not already present) where the common code will reside. This is typically called shared or common.
- Define the `commonMain source` set within the shared module, which will hold the code that is shared across all platforms.

#### Move Common Code to the Shared Module:

- Extract platform-independent code (e.g., business logic, data models) from your Android module and place it into the commonMain source set in the shared module.
- Refactor the code to remove any Android-specific dependencies (e.g., Android SDK classes) from the shared code

#### Handle Platform-Specific Code

- Use expect and actual keywords to define interfaces in the shared module that have platform-specific implementations. 
- For example, if you have a logging utility that needs to behave differently on Android and iOS, define it using expect in commonMain and provide actual implementations in androidMain and iosMain

```kotlin
// In commonMain
expect fun logMessage(message: String)

// In androidMain
actual fun logMessage(message: String) {
    Log.d("AppLog", message)
}

// In iosMain
actual fun logMessage(message: String) {
    println(message)
}

```

#### Update the Android Module

- Refactor the Android-Specific Code:  Replace the extracted logic in the Android module with calls to the shared module’s functions or classes.
- Ensure that the Android module correctly interacts with the shared module, importing and using the shared classes and functions.
- Ensure that the Android module's dependencies are correctly configured to work with the shared module.
- Add any platform-specific dependencies in the androidMain source set if needed

#### Add Support for Additional Platforms

- Add iOS Support (if applicable):  Configure the iOS target in your project. This may involve setting up Xcode, adding CocoaPods dependencies, and ensuring that your shared module is correctly linked to the iOS project.
- Test the iOS-specific functionality and make adjustments as necessary.


- Add JVM/Desktop Support:  Configure the JVM target if you want to support desktop applications. 
- This may involve setting up additional dependencies or configuring specific build tasks for desktop.


- Add JS/Web Support: Configure the JavaScript target if you want to support web applications. 
- You may need to set up a web build environment and test how the shared code interacts with the web platform.

####  Testing and Debugging

- Write Tests for Shared Code: Ensure that the shared code is well-tested. Since the shared module is platform-agnostic, you can write unit tests in the commonTest source set.
- Test platform-specific implementations using the respective platform’s test environment (e.g., Android tests in androidTest, iOS tests in iosTest).
- Debug Across Platforms: Debug the application on all supported platforms to ensure that everything works as expected. Pay special attention to how the shared code integrates with the platform-specific code.
  Build and Deploy

#### Build the Application for All Platforms:

- Use Gradle to build the application for Android, iOS, desktop, and web platforms. Ensure that the build process is smooth and all dependencies are correctly resolved.
- Test the application on real devices (or emulators) for each target platform.
- Deploy the Application:  Deploy the application to the relevant app stores (e.g., Google Play Store for Android, Apple App Store for iOS) or other distribution channels (e.g., web hosting for web apps).

#### Maintenance and Updates

- Ongoing Maintenance: Continue to maintain the shared module and keep it up-to-date with changes that affect all platforms.
- Make sure to regularly update dependencies and address platform-specific issues as they arise.
- Add New Features:  When adding new features, first implement the shared logic in the commonMain source set, and then add any necessary platform-specific code.


## End