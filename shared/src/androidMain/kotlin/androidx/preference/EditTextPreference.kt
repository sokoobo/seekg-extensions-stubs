package androidx.preference

import android.content.Context
import android.widget.EditText

class EditTextPreference(context: Context) : DialogPreference(context) {

    fun getText(): String {
        throw RuntimeException("Stub!")
    }

    fun setText(text: String) {
        throw RuntimeException("Stub!")
    }

    fun setOnBindEditTextListener(onBindEditTextListener: OnBindEditTextListener) {
        throw RuntimeException("Stub!")
    }

    fun getOnBindEditTextListener(): OnBindEditTextListener {
        throw RuntimeException("Stub!")
    }

    interface OnBindEditTextListener {
        fun onBindEditText(editText: EditText)
    }
}
