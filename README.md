# AccessibilityShortcutDisabledSettingEnforcer

## Requirements

Android >= 8.0

A computer with android debug bridge installed and connected to the device (You need to enable developer mode and then USB debugging)

## Installation

1. Download the APK and install it.
2. Grant privileges with ADB `adb shell pm grant com.erikklein.accessibilityshortcutdisabledsettingenforcer android.permission.WRITE_SECURE_SETTINGS`

## What does it do?

It locks down the setting of accessibility shortcut state, to be turned off. Whenever the user tries to enable the shortcut, AccessibilityShortcutDisabledSettingEnforcer detects it and instantly disables it again. Use Andoff One to prevent uninstallation.

## Soon

Autostart has to be implemented, so that the service is started on boot.