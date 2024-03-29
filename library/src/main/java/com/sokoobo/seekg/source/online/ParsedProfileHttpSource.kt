@file:Suppress("unused_parameter")

package com.sokoobo.seekg.source.online

import com.sokoobo.seekg.source.model.Picture
import com.sokoobo.seekg.source.model.ProfileFilterList
import com.sokoobo.seekg.source.model.ProfilesPage
import com.sokoobo.seekg.source.model.SProfile
import com.sokoobo.seekg.source.model.Video
import okhttp3.Headers
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/**
 * A simple implementation for sources from a website using Jsoup, an HTML parser.
 */
abstract class ParsedProfileHttpSource : ProfileHttpSource() {

    protected abstract fun profileSelector(): String
    protected abstract fun profileNextPageSelector(): String?
    protected abstract fun pictureListSelector(): String
    protected abstract fun videoListSelector(): String
    protected abstract fun getProfile(element: Element): SProfile
    protected abstract fun getProfile(document: Document): SProfile
    protected abstract fun getPicture(element: Element): Picture
    protected abstract fun getPicture(document: Document): Picture
    protected abstract fun getVideo(element: Element): Video
    protected abstract fun getVideo(document: Document): Video

    // Overrides
    override suspend fun generateId(name: String, lang: String, versionId: Int): Long {
        throw Exception("Stub!")
    }

    override suspend fun headersBuilder(): Headers.Builder {
        throw Exception("Stub!")
    }

    override suspend fun getUrlWithoutDomain(orig: String): String {
        throw Exception("Stub!")
    }

    // Requests
    override suspend fun createProfilesRequest(page: Int): Request {
        throw Exception("Stub!")
    }

    override suspend fun createProfilesRequest(page: Int, query: String, filters: ProfileFilterList): Request {
        throw Exception("Stub!")
    }

    override suspend fun createProfileRequest(profile: SProfile): Request {
        throw Exception("Stub!")
    }

    override suspend fun getProfile(response: Response): SProfile {
        throw Exception("Stub!")
    }

    override suspend fun getProfiles(response: Response): ProfilesPage {
        throw Exception("Stub!")
    }

    override suspend fun getPictureList(response: Response): List<Picture> {
        throw Exception("Stub!")
    }

    override suspend fun getVideoList(response: Response): List<Video> {
        throw Exception("Stub!")
    }
}