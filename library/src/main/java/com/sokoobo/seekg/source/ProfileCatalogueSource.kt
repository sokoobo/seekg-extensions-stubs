@file:Suppress("UNUSED")

package com.sokoobo.seekg.source

import com.sokoobo.seekg.source.model.ProfileFilterList
import com.sokoobo.seekg.source.model.ProfilesPage

interface ProfileCatalogueSource : ProfileSource {

    override val lang: String

    fun generateId(name: String, lang: String, versionId: Int): Long

    suspend fun getProfiles(page: Int): ProfilesPage
    suspend fun getProfiles(page: Int, query: String, filters: ProfileFilterList): ProfilesPage
    suspend fun getProfileFilterList(): ProfileFilterList
}