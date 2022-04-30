plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.stslex93.notes"
        minSdk = 26
        targetSdk = 32
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
    }

    buildFeatures {
        viewBinding = true
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
}

dependencies {
    implementation(project(mapOf("path" to ":core")))

    /*KSP*/
    val kspVersion = "1.6.21-1.0.5"
    implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")
    implementation("com.google.devtools.ksp:symbol-processing:$kspVersion")
    implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")

    /*Paging*/
    val pagingVersion = "3.1.1"
    implementation("androidx.paging:paging-runtime:$pagingVersion")

    /*Dagger 2*/
    val daggerVersion = "2.41"
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    /*Navigation*/
    val navigationVersion = "2.4.2"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    /*Drawer Layout*/
    implementation("androidx.drawerlayout:drawerlayout:1.1.1")

    /*Coroutines*/
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1-native-mt")

    /*Room*/
    val roomVersion = "2.5.0-alpha01"
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:2.5.0-alpha01")
    ksp("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.7.0-alpha01")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}