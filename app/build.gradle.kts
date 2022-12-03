plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version "1.7.21-1.0.8"
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.stslex93.notes"
        minSdk = 26
        targetSdk = 33
        versionCode = 7
        versionName = "1.07"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        packagingOptions {
            with(resources.excludes) {
                add("kotlin/internal/internal.kotlin_builtins")
                add("kotlin/reflect/reflect.kotlin_builtins")
                add("kotlin/kotlin.kotlin_builtins")
                add("kotlin/coroutines/coroutines.kotlin_builtins")
                add("kotlin/ranges/ranges.kotlin_builtins")
                add("kotlin/collections/collections.kotlin_builtins")
                add("kotlin/annotation/annotation.kotlin_builtins")
            }
        }
        kotlin {
            sourceSets.all {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isTestCoverageEnabled = true
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    sourceSets {
        getByName("androidTest").assets.srcDir("$projectDir/schemas")
    }

    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0-alpha02"
    }
    namespace = "com.stslex93.notes"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-data-notes"))

    /*Paging*/
    val pagingVersion = "3.1.1"
    implementation("androidx.paging:paging-runtime:$pagingVersion")

    /*Dagger 2*/
    val daggerVersion = "2.44.2"
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    /*Navigation*/
    val navigationVersion = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    /*Room*/
    val roomVersion = "2.4.3"
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
}

private val excludeList: List<String> by lazy {
    listOf(
        "**/*Activity*.*",
        "**/*Fragment*.*",
        "**/*FXCMApp*.*",
        "**/*View*.*",
        "**/*Adapter*.*",
        "**/*View.class",
        "**/R.class",
        "**/R$*.class'",
        "**/BuildConfig.*",
        "**/*di*/**'",
        "**/*_MembersInjector.class",
        "**/Dagger*Component.class",
        "**/Dagger*Component*.class",
        "**/*Module_*Factory.class",
        "**/*_Factory*.*"
    )
}