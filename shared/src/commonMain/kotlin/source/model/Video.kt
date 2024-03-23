package source.model

/**
 * The instance that contains the data needed to watch a video.
 */
@Suppress("unused_parameter")
data class Video(val url: String, val quality: String, var videoUrl: String?)
