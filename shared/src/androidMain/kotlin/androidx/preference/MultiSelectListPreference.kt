package androidx.preference

import android.content.Context

class MultiSelectListPreference(context: Context) : DialogPreference(context) {

    fun setEntries(entries: Array<CharSequence>) {
        throw RuntimeException("Stub!")
    }

    fun getEntries(): Array<CharSequence> {
        throw RuntimeException("Stub!")
    }

    fun setEntryValues(entryValues: Array<CharSequence>) {
        throw RuntimeException("Stub!")
    }

    fun getEntryValues(): Array<CharSequence> {
        throw RuntimeException("Stub!")
    }

    fun setValues(values: Set<String>) {
        throw RuntimeException("Stub!")
    }

    fun getValues(): Set<String> {
        throw RuntimeException("Stub!")
    }

    fun findIndexOfValue(value: String): Int {
        throw RuntimeException("Stub!")
    }
}
