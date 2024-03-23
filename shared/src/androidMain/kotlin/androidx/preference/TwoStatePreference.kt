package androidx.preference

import android.content.Context

open class TwoStatePreference(context: Context) : Preference(context) {

    fun isChecked(): Boolean {
        throw RuntimeException("Stub!")
    }

    fun setChecked(checked: Boolean) {
        throw RuntimeException("Stub!")
    }

    fun getSummaryOn(): CharSequence {
        throw RuntimeException("Stub!")
    }

    fun setSummaryOn(summary: CharSequence) {
        throw RuntimeException("Stub!")
    }

    fun getSummaryOff(): CharSequence {
        throw RuntimeException("Stub!")
    }

    fun setSummaryOff(summary: CharSequence) {
        throw RuntimeException("Stub!")
    }

    fun getDisableDependentsState(): Boolean {
        throw RuntimeException("Stub!")
    }

    fun setDisableDependentsState(disableDependentsState: Boolean) {
        throw RuntimeException("Stub!")
    }
}
