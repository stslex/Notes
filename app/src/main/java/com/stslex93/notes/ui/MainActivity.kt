package com.stslex93.notes.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.stslex93.notes.core.navigation.di.NavigationComponentBuilder
import com.stslex93.notes.core.ui.di.MainUiApi
import com.stslex93.notes.core.ui.di.NavigationApi
import com.stslex93.notes.core.ui.theme.AppTheme
import com.stslex93.notes.di.main.MainComponentBuilder

class MainActivity : AppCompatActivity(), MainUiApi {

    private var _navigationApi: NavigationApi? = null
    override val navigationApi: NavigationApi
        get() = requireNotNull(_navigationApi)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberNavController()
            _navigationApi = NavigationComponentBuilder
                .build(navController)
                .also { api ->
                    MainComponentBuilder.Build(api)
                }

            AppTheme {
                AppInit(navController)
            }
        }
    }
}
