@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.model

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
