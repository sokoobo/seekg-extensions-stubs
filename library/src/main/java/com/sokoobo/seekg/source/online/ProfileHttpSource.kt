@file:Suppress("unused_parameter")

package com.sokoobo.seekg.source.online

import com.sokoobo.seekg.network.NetworkHelper
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request

import com.sokoobo.seekg.source.ProfileCatalogueSource
import com.sokoobo.seekg.source.model.Picture
import com.sokoobo.seekg.source.model.ProfileFilterList
import com.sokoobo.seekg.source.model.ProfilesPage
import com.sokoobo.seekg.source.model.SProfile
import com.sokoobo.seekg.source.model.Video
import okhttp3.Response

abstract class ProfileHttpSource : ProfileCatalogueSource {

    protected val network: NetworkHelper = throw Exception("Stub!")
    abstract val baseUrl: String

    override val id: Long = throw Exception("Stub!")
    override val name: String = throw Exception("Stub!")
    override val versionId: Int = throw Exception("Stub!")

    val headers: Headers = throw Exception("Stub!")

    open val client: OkHttpClient = throw Exception("Stub!")

    // Common
    protected abstract suspend fun headersBuilder(): Headers.Builder
    protected abstract suspend fun getUrlWithoutDomain(orig: String): String

    // Requests
    protected abstract suspend fun createProfilesRequest(page: Int): Request
    protected abstract suspend fun createProfilesRequest(page: Int, query: String, filters: ProfileFilterList): Request
    protected abstract suspend fun createProfileRequest(profile: SProfile): Request

    // Responses
    protected abstract suspend fun getProfile(response: Response): SProfile
    protected abstract suspend fun getProfiles(response: Response): ProfilesPage
    protected abstract suspend fun getPictureList(response: Response): List<Picture>
    protected abstract suspend fun getVideoList(response: Response): List<Video>

    // Commons / Overrides
    override fun toString(): String {
        throw Exception("Stub!")
    }

    // ProfileSource / Overrides
    override suspend fun getProfileDetails(profile: SProfile): SProfile {
        throw Exception("Stub!")
    }

    override suspend fun getPictureList(profile: SProfile): List<Picture> {
        throw Exception("Stub!")
    }

    override suspend fun getVideoList(profile: SProfile): List<Video> {
        throw Exception("Stub!")
    }

    // ProfileCatalogueSource / Overrides
    override suspend fun getProfiles(page: Int): ProfilesPage {
        throw Exception("Stub!")
    }

    override suspend fun getProfiles(
        page: Int,
        query: String,
        filters: ProfileFilterList
    ): ProfilesPage {
        throw Exception("Stub!")
    }

    override suspend fun getProfileFilterList(): ProfileFilterList {
        throw Exception("Stub!")
    }
}