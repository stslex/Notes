import com.android.build.api.dsl.ApplicationExtension
import com.stslex93.notes.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                namespace = "com.stslex93.notes"

                defaultConfig.apply {
                    applicationId = "com.stslex93.notes"
                    targetSdk = 34
                    versionCode = AppVersion.VERSION_CODE
                    versionName = AppVersion.VERSION_NAME

                    signingConfigs {
                        val keystoreProperties =
                            gradleKeystoreProperties(project.rootProject.projectDir)
                        val keyStore = getFile(
                            projectRootDir = project.rootProject.projectDir,
                            path = keystoreProperties.getProperty("storeFile")
                        )
                        create("release") {
                            keyAlias = keystoreProperties.getProperty("keyAlias")
                            keyPassword = keystoreProperties.getProperty("keyPassword")
                            storeFile = keyStore
                            storePassword = keystoreProperties.getProperty("storePassword")
                        }
                        with(getByName("debug")) {
                            keyAlias = keystoreProperties.getProperty("keyAlias")
                            keyPassword = keystoreProperties.getProperty("keyPassword")
                            storeFile = keyStore
                            storePassword = keystoreProperties.getProperty("storePassword")
                        }
                    }
                    buildTypes {
                        release {
                            isMinifyEnabled = false
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                            signingConfig = signingConfigs.getByName("release")
                            isDebuggable = false
                        }
                        debug {
                            signingConfig = signingConfigs.getByName("debug")
                            isDebuggable = true
                        }
                    }
                }
                buildFeatures.viewBinding = true
            }
        }
    }
}

fun getFile(
    projectRootDir: File,
    path: String
): File {
    val file = File(projectRootDir, path)
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
