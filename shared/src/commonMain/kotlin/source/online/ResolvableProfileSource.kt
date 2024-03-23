package source.online

import source.ProfileSource
import source.model.SProfile

/**
 * A source that may handle opening an SProfile for a given URI.
 */
interface ResolvableProfileSource : ProfileSource {

    /**
     * Returns what the given URI may open.
     * Returns [UriType.Unknown] if the source is not able to resolve the URI.
     */
    fun getUriType(uri: String): UriType

    /**
     * Called if [getUriType] is [UriType.Profile].
     * Returns the corresponding [SProfile], if possible.
     */
    suspend fun getProfile(uri: String): SProfile?
}

sealed interface UriType {
    data object Profile : UriType
    data object Unknown : UriType
}