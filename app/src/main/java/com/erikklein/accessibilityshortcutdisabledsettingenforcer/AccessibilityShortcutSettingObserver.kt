package com.erikklein.accessibilityshortcutdisabledsettingenforcer

import android.content.Context
import android.database.ContentObserver
import android.os.Handler
import android.provider.Settings
import android.util.Log

class AccessibilityShortcutSettingObserver(
    handler: Handler,
    private val context: Context
) : ContentObserver(handler) {

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)

        try {
            // Get the current value of the setting
            val currentValue = Settings.Secure.getInt(
                context.contentResolver,
                "accessibility_shortcut_enabled"
            )

            // Desired value for the setting
            val desiredValue = 0

            // Revert the setting if it has been changed
            if (currentValue != desiredValue) {
                Settings.Secure.putInt(
                    context.contentResolver,
                    "accessibility_shortcut_enabled",
                    desiredValue
                )
                Log.d("SettingsObserver", "AccessibilityShortcutEnabled reverted to $desiredValue")
            }
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }
    }
}
