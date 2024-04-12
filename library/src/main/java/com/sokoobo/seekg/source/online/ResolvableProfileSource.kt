@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.online

import com.sokoobo.seekg.source.ProfileSource
import com.sokoobo.seekg.source.model.SProfile

interface ResolvableProfileSource : ProfileSource {

    fun getUriType(uri: String): UriType
    suspend fun getProfile(uri: String): SProfile?
}

sealed interface UriType {
    data object Profile : UriType
    data object Unknown : UriType
}