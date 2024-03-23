package source

import source.model.ProfileFilterList

interface ProfileCatalogueSource : ProfileSource {

    /**
     * An ISO 639-1 compliant language code (two letters in lower case).
     */
    val lang: String

    /**
     * Whether the source has support for latest updates.
     */
    val supportsLatest: Boolean

    /**
     * Get a page with a list of popular profile.
     */
    suspend fun getPopularProfiles(page: Int): ProfilesPage {
        throw Exception("Stub!")
    }

    /**
     * Get a page with a list of profile.
     */
    suspend fun getSearchProfiles(page: Int, query: String, filters: ProfileFilterList): ProfilesPage {
        throw Exception("Stub!")
    }

    /**
     * Returns the list of filters for the source.
     */
    fun getFilterList(): ProfileFilterList
}