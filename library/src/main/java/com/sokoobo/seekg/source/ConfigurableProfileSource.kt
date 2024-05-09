@file:Suppress("UNUSED")

package com.sokoobo.seekg.source

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceScreen
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

interface ConfigurableProfileSource : ProfileSource {
    fun getSourcePreferences(): SharedPreferences =
        Injekt.get<Application>().getSharedPreferences(preferenceKey(), 0)

    fun setupPreferenceScreen(screen: PreferenceScreen)
}

fun ConfigurableProfileSource.preferenceKey(): String = "source_$id"