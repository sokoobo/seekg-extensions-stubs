package androidx.preference

import android.content.Context

@Suppress("unused_parameter")
open class Preference(context: Context) {
    var onPreferenceChangeListener: OnPreferenceChangeListener? = null
    var onPreferenceClickListener: OnPreferenceClickListener? = null
    var title: CharSequence? = null
    var summary: CharSequence? = null
    var isEnabled: Boolean = true
    var isVisible: Boolean = true
    var key: String? = null
    var defaultValue: Any? = null

    interface OnPreferenceChangeListener {
        fun onPreferenceChange(preference: Preference, newValue: Any): Boolean
    }

    interface OnPreferenceClickListener {
        fun onPreferenceClick(preference: Preference): Boolean
    }
}
