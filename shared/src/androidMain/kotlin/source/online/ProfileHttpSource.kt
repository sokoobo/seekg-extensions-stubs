package source.online

import network.NetworkHelper
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import rx.Observable

import source.ProfileCatalogueSource
import source.ProfilesPage
import source.model.Picture
import source.model.ProfileFilterList
import source.model.SProfile
import source.model.Video

/**
 * A simple implementation for sources from a website.
 * Usually requires the usage of json serialization or similar techniques.
 */
@Suppress("unused", "unused_parameter")
abstract class ProfileHttpSource : ProfileCatalogueSource {

    /**
     * Network service.
     */
    protected val network: NetworkHelper = throw Exception("Stub!")

    /**
     * Base url of the website without the trailing slash, like: http://mysite.com
     */
    abstract val baseUrl: String

    /**
     * Version id used to generate the source id. If the site completely changes and urls are
     * incompatible, you may increase this value and it'll be considered as a new source.
     */
    open val versionId: Int = throw Exception("Stub!")

    /**
     * ID of the source. By default it uses a generated id using the first 16 characters (64 bits)
     * of the MD5 of the string `"${name.lowercase()}/$lang/$versionId"`.
     *
     * The ID is generated by the [generateId] function, which can be reused if needed
     * to generate outdated IDs for cases where the source name or language needs to
     * be changed but migrations can be avoided.
     *
     * Note: the generated ID sets the sign bit to `0`.
     */
    override val id: Long = throw Exception("Stub!")

    /**
     * Headers used for requests. Result of [headersBuilder]
     */
    val headers: Headers = throw Exception("Stub!")

    /**
     * Default network client for doing requests. Implementations can override this property
     * for custom [OkHttpClient] instances.
     *
     * **Usage example:**
     * ```
     * import okhttp3.Dns
     * .....
     * override val client: OkHttpClient =
     *     network.client
     *         .newBuilder()
     *         .addInterceptor(RecaptchaDestroyer())
     *         .dns(Dns.SYSTEM)
     *         .build()
     * ```
     */
    open val client: OkHttpClient = throw Exception("Stub!")

    /**
     * Generates a unique ID for the source based on the provided [name], [lang] and
     * [versionId]. It will use the first 16 characters (64 bits) of the MD5 of the string
     * `"${name.lowercase()}/$lang/$versionId"`.
     *
     * Note: the generated ID sets the sign bit to `0`.
     *
     * Can be used to generate outdated IDs, such as when the source name or language
     * needs to be changed but migrations can be avoided.
     *
     * @since extensions-lib 14
     * @param name [String] the name of the source
     * @param lang [String] the language of the source
     * @param versionId [Int] the version ID of the source
     * @return a unique ID for the source
     */
    protected fun generateId(name: String, lang: String, versionId: Int): Long {
        throw Exception("Stub!")
    }

    /**
     * Headers builder for requests. Implementations can override this method for custom headers.
     *
     * **Usage examples:**
     * ```
     * // Adds headers to the default [Headers.Builder] instance, retaining
     * // headers like the default(or user-made) User-Agent.
     * override fun headersBuilder() = super.headersBuilder().add("Referer", baseUrl)
     * ```
     * ```
     * // Creates a new, empty [Headers.Builder] instance and adds a single header.
     * override fun headersBuilder() = Headers.Builder().add("Referer", baseUrl)
     * ```
     */
    protected open fun headersBuilder(): Headers.Builder {
        throw Exception("Stub!")
    }

    /**
     * Visible name of the source.
     */
    override fun toString(): String {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for the popular profile given the page.
     *
     * @param page the page number to retrieve.
     */
    protected abstract fun popularProfileRequest(page: Int): Request

    /**
     * Parses the response from the site and returns a [ProfilesPage] object.
     *
     * @param response the response from the site.
     */
    protected abstract fun popularProfileParse(response: Response): ProfilesPage

    /**
     * Returns the request for the search profile given the page and filters.
     *
     * @param page the page number to retrieve.
     * @param query the search query.
     * @param filters the list of filters to apply.
     */
    protected abstract fun searchProfileRequest(page: Int, query: String, filters: ProfileFilterList): Request

    /**
     * Parses the response from the site and returns a [ProfilesPage] object.
     *
     * @param response the response from the site.
     */
    protected abstract fun searchProfileParse(response: Response): ProfilesPage

    /**
     * Get the updated details for a profile.
     */
    override suspend fun getProfileDetails(profile: SProfile): SProfile {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for the details of a profile. Override only if it's needed to change the
     */
    open fun profileDetailsRequest(profile: SProfile): Request {
        throw Exception("Stub!")
    }

    /**
     * Parses the response from the site and returns the details of a profile.
     *
     * @param response the response from the site.
     */
    protected abstract fun profileDetailsParse(response: Response): SProfile

    /**
     * Get all the available episodes for an anime.
     * Normally it's not needed to override this method.
     */
    override suspend fun getPictureList(profile: SProfile): List<Picture> {
        throw Exception("Stub!")
    }

    /**
     * Get the list of videos a episode has. Videos should be returned
     * in the expected order; the index is ignored.
     */
    override suspend fun getVideoList(profile: SProfile): List<Video> {
        throw Exception("Stub!")
    }

    open fun fetchVideoUrl(video: Video): Observable<String> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for updating the episode list. Override only if it's needed to override
     * the url, send different headers or request method like POST.
     */
    protected open fun pictureListRequest(profile: SProfile): Request {
        throw Exception("Stub!")
    }

    /**
     * Parses the response from the site and returns a list of pictures.
     */
    protected abstract fun pictureListParse(response: Response): List<Picture>

    /**
     * Returns the request for getting the video list. Override only if it's needed to override
     * the url, send different headers or request method like POST.
     */
    protected open fun videoListRequest(profile: SProfile): Request {
        throw Exception("Stub!")
    }

    /**
     * Parses the response from the site and returns a list of videos.
     *
     * @param response the response from the site.
     */
    protected open fun videoListParse(response: Response): List<Video> {
        throw Exception("Stub!")
    }

    /**
     * Sorts the video list.
     * Override this according to the user's preference.
     *
     * **Usage examples:**
     * ```
     * // Sorts by quality
     * override fun List<Video>.sort(): List<Video> {
     *     val quality = preferences.getString(PREF_QUALITY_KEY, PREF_QUALITY_DEFAULT)!!
     *     return sortedWith(
     *         compareBy { it.quality.contains(quality) }
     *     ).reversed()
     * }
     * ```
     * ```
     * // Sorts by quality and hard sub language
     * override fun List<Video>.sort(): List<Video> {
     *    val quality = preferences.getString(PREF_QUALITY_KEY, PREF_QUALITY_DEFAULT)!!
     *    val lang = preferences.getString(PREF_LANG_KEY, PREF_LANG_DEFAULT)!!
     *    return sortedWith(
     *        compareBy(
     *            { it.quality.contains(quality) },
     *            { it.quality.contains(lang) },
     *        ),
     *    ).reversed()
     * }
     * ```
     */
    protected open fun List<Video>.sort(): List<Video> {
        throw Exception("Stub!")
    }

    /**
     * Returns the request for getting the url to the source video. Override only if it's needed to
     * override the url, send different headers or request method like POST.
     */
    protected open fun videoUrlRequest(video: Video): Request {
        throw Exception("Stub!")
    }

    protected open fun videoUrlParse(response: Response): String {
        throw Exception("Stub!")
    }

    /**
     * Returns the url of the given string without the scheme and domain.
     *
     * @param orig the full url.
     */
    private fun getUrlWithoutDomain(orig: String): String {
        throw Exception("Stub!")
    }

    /**
     * Returns the url of the provided anime. Useful to fix "open in web-view"
     * without overriding [getProfileDetails].
     */
    open fun getProfileUrl(profile: SProfile): String {
        throw Exception("Stub!")
    }

    /**
     * Returns the url of the provided picture.
     */
    open fun getPictureUrl(profile: SProfile): String {
        throw Exception("Stub!")
    }

    /**
     * Called before inserting a new picture into database. Use it if you need to override picture
     * Do not change anything to [profile].
     */
    open fun prepareNewPicture(profile: SProfile) {}

    /**
     * Returns the list of filters for the source.
     */
    override fun getFilterList(): ProfileFilterList {
        throw Exception("Stub!")
    }
}