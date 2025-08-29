import AppExt.findPluginId
import AppExt.libs
import com.android.build.api.dsl.LibraryExtension
import com.stslex93.notes.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply(libs.findPluginId("kotlin"))
            pluginManager.apply(libs.findPluginId("composeCompiler"))
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}