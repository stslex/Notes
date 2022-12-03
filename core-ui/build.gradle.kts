plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.stslex93.core_ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
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
    implementation(project(":core"))
    implementation(project(":core-test"))

    /*Compose*/
    api("androidx.compose.material3:material3:1.0.1")
    api("androidx.compose.ui:ui:1.3.1")
    api("androidx.activity:activity-compose:1.6.1")

    /*Drawer Layout*/
    api("androidx.drawerlayout:drawerlayout:1.1.1")

    api("com.google.android.material:material:1.7.0")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
}