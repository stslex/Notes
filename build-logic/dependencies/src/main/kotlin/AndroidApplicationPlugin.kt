import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.stslex93.notes.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.version

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")

                apply("kotlin-kapt")
                apply("androidx.navigation.safeargs.kotlin")
                apply("kotlin-parcelize")
                apply("com.google.devtools.ksp")
//                apply("org.jetbrains.kotlinx.kover")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                namespace = "com.stslex93.notes"

                defaultConfig.apply {
                    applicationId = "com.stslex93.notes"
                    targetSdk = 34
                    versionCode = 7
                    versionName = "1.07"
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
                buildFeatures.viewBinding = true
            }
        }
    }
}