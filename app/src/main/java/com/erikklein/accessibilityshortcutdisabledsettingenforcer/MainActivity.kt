package com.erikklein.accessibilityshortcutdisabledsettingenforcer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.erikklein.accessibilityshortcutdisabledsettingenforcer.ui.theme.AccessibilityShortcutDisabledSettingEnforcerTheme

fun startSettingsMonitorService(context: Context) {
    val serviceIntent = Intent(context, Enforcer::class.java)
    context.startForegroundService(serviceIntent)
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startSettingsMonitorService(this)
        setContent {
            AccessibilityShortcutDisabledSettingEnforcerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "Enforcer started!",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
