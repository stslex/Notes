import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    id("notes.android.application")
    id("notes.android.application.compose")
}

android.namespace = "com.stslex93.notes"

android {
    signingConfigs {
        val keystoreProperties = gradleKeystoreProperties(project.rootProject.projectDir)
        create("release") {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = getFile(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
        with(get("debug")) {
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
            storeFile = getFile(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:database"))
    implementation(project(":core:notes"))

    implementation(project(":feature:edit"))

    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)
    androidTestApi(libs.androidx.room.testing)

    implementation(libs.androidx.paging.runtime)

    // TODO remove after compose integration
    val navigationVersion = "2.5.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}

fun getFile(path: String): File {
    val file = File(project.rootProject.projectDir, path)
    if (file.isFile) {
        return file
    } else {
        throw IllegalStateException("${file.name} is inValid")
    }
}

fun gradleKeystoreProperties(projectRootDir: File): Properties {
    val properties = Properties()
    val localProperties = File(projectRootDir, "keystore.properties")

    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    }
    return properties
}
