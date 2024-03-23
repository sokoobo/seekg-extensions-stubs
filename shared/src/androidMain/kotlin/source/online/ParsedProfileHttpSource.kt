package source.online

import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import source.ProfilesPage
import source.model.Picture
import source.model.SProfile
import source.model.Video

/**
 * A simple implementation for sources from a website using Jsoup, an HTML parser.
 */
@Suppress("unused", "unused_parameter")
abstract class ParsedAnimeHttpSource : ProfileHttpSource() {

    /**
     * Parses the response from the site and returns a [ProfilesPage] object.
     * @param response the response from the site.
     */
    override fun popularProfileParse(response: Response): ProfilesPage {
        throw Exception("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each profile.
     */
    protected abstract fun popularProfileSelector(): String

    /**
     * Returns a anime from the given [element]. Most sites only show the title and the url, it's
     * totally fine to fill only those two values.
     */
    protected abstract fun popularProfileFromElement(element: Element): SProfile

    /**
     * Returns the Jsoup selector that returns the <a> tag linking to the next page, or null if
     * there's no next page.
     */
    protected abstract fun popularProfileNextPageSelector(): String?

    /**
     * Parses the response from the site and returns a [ProfilesPage] object.
     *
     * @param response the response from the site.
     */
    override fun searchProfileParse(response: Response): ProfilesPage {
        throw Exception("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each profile.
     */
    protected abstract fun searchProfileSelector(): String

    /**
     * Returns a anime from the given [element]. Most sites only show the title and the url, it's
     * totally fine to fill only those two values.
     */
    protected abstract fun searchProfileFromElement(element: Element): SProfile

    /**
     * Returns the Jsoup selector that returns the <a> tag linking to the next page, or null if
     * there's no next page.
     */
    protected abstract fun searchProfileNextPageSelector(): String?

    /**
     * Parses the response from the site and returns the details of a profile.
     *
     * @param response the response from the site.
     */
    override fun profileDetailsParse(response: Response): SProfile {
        throw Exception("Stub!")
    }

    /**
     * Returns the details of the profile from the given [document].
     *
     * @param document the parsed document.
     */
    protected abstract fun profileDetailsParse(document: Document): SProfile

    /**
     * Parses the response from the site and returns a list of pictures.
     *
     * @param response the response from the site.
     */
    override fun pictureListParse(response: Response): List<Picture> {
        throw Exception("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each picture.
     */
    protected abstract fun pictureListSelector(): String

    /**
     * Returns a picture from the given element.
     *
     * @param element an element obtained from [pictureListSelector].
     */
    protected abstract fun pictureFromElement(element: Element): Picture

    /**
     * Parses the response from the site and returns a list of videos.
     *
     * @param response the response from the site.
     */
    override fun videoListParse(response: Response): List<Video> {
        throw Exception("Stub!")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each video.
     */
    protected abstract fun videoListSelector(): String

    /**
     * Returns a video from the given element.
     *
     * @param element an element obtained from [videoListSelector].
     */
    protected abstract fun videoFromElement(element: Element): Video

    override fun videoUrlParse(response: Response): String {
        throw Exception("Stub!")
    }

    /**
     * Returns the absolute url to the video url from the document.
     *
     * @param document the parsed document.
     */
    protected abstract fun videoUrlParse(document: Document): String
}