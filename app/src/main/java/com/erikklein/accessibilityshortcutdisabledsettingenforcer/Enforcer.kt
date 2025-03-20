package com.erikklein.accessibilityshortcutdisabledsettingenforcer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.provider.Settings

class Enforcer : Service() {

    private lateinit var settingsObserver: AccessibilityShortcutSettingObserver

    override fun onCreate() {
        super.onCreate()

        val channelId = "settings_monitor_channel"
        val channelName = "Settings Monitor"
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_LOW
        )
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)

        // Initialize and register the ContentObserver
        settingsObserver = AccessibilityShortcutSettingObserver(Handler(), this)
        contentResolver.registerContentObserver(
            Settings.Secure.CONTENT_URI,
            true,
            settingsObserver
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification = Notification.Builder(this, "settings_monitor_channel")
            .setContentTitle("Settings Monitor")
            .setContentText("Monitoring system settings...")
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .build()

        startForeground(1, notification)
        // Keep the service running
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        // Unregister the ContentObserver
        contentResolver.unregisterContentObserver(settingsObserver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
