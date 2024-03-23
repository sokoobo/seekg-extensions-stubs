package androidx.preference

import android.content.Context

class ListPreference(context: Context) : Preference(context) {

    fun getEntries(): Array<CharSequence> {
        throw RuntimeException("Stub!")
    }

    fun setEntries(entries: Array<CharSequence>) {
        throw RuntimeException("Stub!")
    }

    fun findIndexOfValue(value: String): Int {
        throw RuntimeException("Stub!")
    }

    fun getEntryValues(): Array<CharSequence> {
        throw RuntimeException("Stub!")
    }

    fun setEntryValues(entryValues: Array<CharSequence>) {
        throw RuntimeException("Stub!")
    }

    fun setValueIndex(index: Int) {
        throw RuntimeException("Stub!")
    }

    fun getValue(): String {
        throw RuntimeException("Stub!")
    }

    fun setValue(value: String) {
        throw RuntimeException("Stub!")
    }
}
