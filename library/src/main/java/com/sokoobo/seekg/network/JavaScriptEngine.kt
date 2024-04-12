@file:Suppress("UNUSED", "UNCHECKED_CAST")

package com.sokoobo.seekg.network

import android.content.Context
import app.cash.quickjs.QuickJs
import com.sokoobo.seekg.util.withIOContext

class JavaScriptEngine(context: Context) {
    suspend fun <T> evaluate(script: String): T = withIOContext {
        QuickJs.create().use {
            it.evaluate(script) as T
        }
    }
}
