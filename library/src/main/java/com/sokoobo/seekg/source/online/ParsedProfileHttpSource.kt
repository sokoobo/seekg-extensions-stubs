@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.online

import com.sokoobo.seekg.source.model.Picture
import com.sokoobo.seekg.source.model.ProfilesPage
import com.sokoobo.seekg.source.model.SProfile
import com.sokoobo.seekg.source.model.Video
import com.sokoobo.seekg.util.asJsoup
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
    protected abstract fun getVideo(element: Element): Video

    override suspend fun getProfile(response: Response): SProfile {
        return getProfile(response.asJsoup())
    }

    override suspend fun getProfiles(response: Response): ProfilesPage {
        val document = response.asJsoup()
        val profiles = document.select(profileSelector()).map { element ->
            getProfile(element)
        }
        val hasNextPage = profileNextPageSelector()?.let { selector ->
            document.select(selector).first()
        } != null
        return ProfilesPage(profiles, hasNextPage)
    }

    override suspend fun getPictureList(response: Response): List<Picture> {
        val document = response.asJsoup()
        return document.select(pictureListSelector()).map { getPicture(it) }
    }

    override suspend fun getVideoList(response: Response): List<Video> {
        val document = response.asJsoup()
        return document.select(videoListSelector()).map { getVideo(it) }
    }
}