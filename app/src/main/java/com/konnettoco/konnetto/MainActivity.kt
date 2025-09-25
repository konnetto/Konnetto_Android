package com.konnettoco.konnetto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.konnettoco.konnetto.ui.screen.settings.SettingsViewModel
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import com.konnettoco.konnetto.ui.viewModelFactory.SettingsViewModelFactory


class MainActivity : ComponentActivity() {
    private val viewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            !viewModel.isThemeLoaded.value
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsState(initial = false)

            KonnettoTheme(darkTheme = isDarkTheme)  {
                // A surface container using the background color from theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KonnettoApp()
                }
            }
        }
    }
}
