import com.android.build.gradle.LibraryExtension
import com.stslex93.notes.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.apply {
                    targetSdk = 34
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                    buildTypes {
                        release {
                            isMinifyEnabled = false
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }
                }
            }
        }
    }
}