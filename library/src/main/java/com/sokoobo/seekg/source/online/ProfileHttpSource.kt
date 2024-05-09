@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.online

import com.sokoobo.seekg.network.GET
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
import okhttp3.Cache
import okhttp3.CookieJar
import okhttp3.Response
import uy.kohesive.injekt.injectLazy
import java.net.URI
import java.net.URISyntaxException
import java.security.MessageDigest

abstract class ProfileHttpSource : ProfileCatalogueSource {

    protected val network: NetworkHelper by injectLazy()
    abstract val baseUrl: String

    override val id: Long by lazy { generateId(name, lang, versionId) }
    override val name: String = ""
    override val versionId: Int = 1

    open val headers: Headers by lazy { headersBuilder().build() }

    open fun client(cache: Cache? = null): OkHttpClient {
        return network.client(CookieJar.NO_COOKIES, cache)
    }

    open fun client(cookieJar: CookieJar = CookieJar.NO_COOKIES, cache: Cache? = null): OkHttpClient {
        return network.client(cookieJar, cache)
    }

    // Common
    protected fun getUrlWithoutDomain(orig: String): String {
        return try {
            val uri = URI(orig)
            var out = uri.path
            if (uri.query != null) {
                out += "?" + uri.query
            }
            if (uri.fragment != null) {
                out += "#" + uri.fragment
            }
            out
        } catch (e: URISyntaxException) {
            orig
        }
    }

    // Requests
    protected abstract suspend fun createProfilesRequest(page: Int): Request
    protected abstract suspend fun createProfilesRequest(
        page: Int,
        query: String,
        filters: ProfileFilterList
    ): Request

    protected open fun createProfileRequest(profile: SProfile): Request {
        return GET(baseUrl + profile.url, headers)
    }

    // Responses
    protected abstract suspend fun getProfile(response: Response): SProfile
    protected abstract suspend fun getProfiles(response: Response): ProfilesPage
    protected abstract suspend fun getPictureList(response: Response): List<Picture>
    protected abstract suspend fun getVideoList(response: Response): List<Video>

    // Commons / Overrides
    override fun toString() = "$name (${lang.uppercase()})"

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
    override fun generateId(name: String, lang: String, versionId: Int): Long {
        val key = "${name.lowercase()}/$lang/$versionId"
        val bytes = MessageDigest.getInstance("MD5").digest(key.toByteArray())
        return (0..7).map { bytes[it].toLong() and 0xff shl 8 * (7 - it) }
            .reduce(Long::or) and Long.MAX_VALUE
    }

    protected open fun headersBuilder() = Headers.Builder().apply {
        add("User-Agent", network.defaultUserAgentProvider())
    }

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