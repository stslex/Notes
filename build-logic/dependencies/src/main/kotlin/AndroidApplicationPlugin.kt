import AppExt.APP_PREFIX
import AppExt.findPluginId
import AppExt.findVersionInt
import AppExt.findVersionString
import AppExt.libs
import com.android.build.api.dsl.ApplicationExtension
import com.stslex93.notes.configureKotlinAndroid
import com.stslex93.notes.configureKover
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
            pluginManager.apply {
                apply(libs.findPluginId("application"))
                apply(libs.findPluginId("kotlin"))
                apply(libs.findPluginId("ksp"))
                apply(libs.findPluginId("kover"))
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureKover()
                namespace = APP_PREFIX

                defaultConfig.apply {
                    applicationId = APP_PREFIX
                    targetSdk = libs.findVersionInt("targetSdk")
                    versionName = libs.findVersionString("versionName")
                    versionCode = libs.findVersionInt("versionCode")

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
