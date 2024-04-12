@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.model

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val url: String,
    val quality: String,
    var videoUrl: String?
) {
    @Transient
    @Volatile
    var status: State = State.QUEUE
        set(value) {
            field = value
        }

    enum class State {
        QUEUE,
        LOAD,
        READY,
        ERROR,
    }
}
