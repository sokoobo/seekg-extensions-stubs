package source

import source.model.Video
import source.model.Picture
import source.model.SProfile

/**
 * A basic interface for creating a source. It could be an online source, a local source, etc...
 */
interface ProfileSource {

    /**
     * Id for the source. Must be unique.
     */
    val id: Long

    /**
     * Name of the source.
     */
    val name: String

    /**
     * Get the updated details for a profile.
     */
    suspend fun getProfileDetails(profile: SProfile): SProfile

    /**
     * Get all pictures for a profile.
     */
    suspend fun getPictureList(profile: SProfile): List<Picture>

    /**
     * Get all videos for a profile.
     */
    suspend fun getVideoList(profile: SProfile): List<Video>
}