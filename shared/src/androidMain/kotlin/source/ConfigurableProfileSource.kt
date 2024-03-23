package source

import androidx.preference.PreferenceScreen

/**
 * A interface to add user preferences to the source.
 */
interface ConfigurableProfileSource {
    /**
     * Implementations must override this method to add the user preferences.
     *
     * You can use some stubbed inheritors of [androidx.preference.Preference] here.
     *
     * **Common usage example:**
     */
    fun setupPreferenceScreen(screen: PreferenceScreen)
}
