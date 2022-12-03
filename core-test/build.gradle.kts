plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.stslex93.core_test"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val robolectricVersion = "4.9"
    api("org.robolectric:robolectric:$robolectricVersion")
    api("androidx.test:core-ktx:1.5.0")

    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.4")
    api("androidx.test.espresso:espresso-core:3.5.0")

    androidTestApi("androidx.room:room-testing:2.4.3")
}