@file:Suppress("unused_parameter")

package com.sokoobo.seekg.source.model

/**
 * The instance that contains the data needed to watch a video.
 */
data class Video(val url: String, val quality: String, var videoUrl: String?)
