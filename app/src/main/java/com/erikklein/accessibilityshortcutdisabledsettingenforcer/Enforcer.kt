package com.erikklein.accessibilityshortcutdisabledsettingenforcer

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.provider.Settings

class Enforcer : Service() {

    private lateinit var settingsObserver: AccessibilityShortcutSettingObserver

    override fun onCreate() {
        super.onCreate()

        // Initialize and register the ContentObserver
        settingsObserver = AccessibilityShortcutSettingObserver(Handler(), this)
        contentResolver.registerContentObserver(
            Settings.Secure.CONTENT_URI,
            true,
            settingsObserver
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
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
