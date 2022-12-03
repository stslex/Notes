plugins {
    id("notes.android.library")
}

dependencies {
    libs.apply {
        api(androidx.core.ktx)
        api(bundles.ksp)
        api(kotlinx.coroutines)
        api(kotlin.stdlib)
        api(androidx.appcompat)
    }
}

android.namespace = "com.stslex93.core"
