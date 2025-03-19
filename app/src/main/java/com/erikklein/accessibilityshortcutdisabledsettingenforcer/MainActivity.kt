package com.erikklein.accessibilityshortcutdisabledsettingenforcer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.erikklein.accessibilityshortcutdisabledsettingenforcer.ui.theme.AccessibilityShortcutDisabledSettingEnforcerTheme

fun startSettingsMonitorService(context: Context) {
    val serviceIntent = Intent(context, Enforcer::class.java)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "settings_monitor_channel"
        val channelName = "Settings Monitor"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)

        val notification: Notification = Notification.Builder(context, channelId)
            .setContentTitle("Settings Monitor")
            .setContentText("Monitoring system settings...")
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .build()

        context.startForegroundService(serviceIntent)
    } else {
        context.startService(serviceIntent)
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AccessibilityShortcutDisabledSettingEnforcerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        startSettingsMonitorService(this)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AccessibilityShortcutDisabledSettingEnforcerTheme {
        Greeting("Android")
    }
}