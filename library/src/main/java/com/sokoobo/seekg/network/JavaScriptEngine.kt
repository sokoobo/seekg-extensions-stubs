@file:Suppress("unused_parameter")

package com.sokoobo.seekg.network

import android.content.Context

/**
 * Util for evaluating JavaScript in sources.
 */
class JavaScriptEngine(context: Context) {
    suspend fun <T> evaluate(script: String): T = throw Exception("Stub!")
}
