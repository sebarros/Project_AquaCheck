package cl.aquacheck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.aquacheck.ui.AquaCheckApp
import cl.aquacheck.ui.theme.AquaCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquaCheckTheme {
                AquaCheckApp()
            }
        }
    }
}
