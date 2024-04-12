@file:Suppress("UNUSED")

package com.sokoobo.seekg.source

interface ProfileSourceFactory {
    fun createSources(): List<ProfileSource>
}
