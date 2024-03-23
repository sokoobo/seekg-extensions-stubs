package androidx.preference

import android.content.Context

abstract class DialogPreference(context: Context) : Preference(context) {

    fun getDialogTitle(): CharSequence {
        throw RuntimeException("Stub!")
    }

    fun setDialogTitle(dialogTitle: CharSequence) {
        throw RuntimeException("Stub!")
    }

    fun getDialogMessage(): CharSequence {
        throw RuntimeException("Stub!")
    }

    fun setDialogMessage(dialogMessage: CharSequence) {
        throw RuntimeException("Stub!")
    }
}

