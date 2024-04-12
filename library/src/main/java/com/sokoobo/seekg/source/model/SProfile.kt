@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.model

interface SProfile {

    var id: String

    var url: String

    var description: String?

    var tel: String?

    var title: String

    var thumb: String?

    var city: String?

    var age: Int

    var pictures: Set<String>?

    var videos: Set<String>?

    var tags: String?

    fun getGenres(): List<String>? {
        if (tags.isNullOrBlank()) return null
        return tags?.split(", ")?.map { it.trim() }?.filterNot { it.isBlank() }?.distinct()
    }

    fun copy() = create().also {
        it.id = id
        it.url = url
        it.title = title
        it.tel = tel
        it.thumb = thumb
        it.description = description
        it.city = city
        it.age = age
        it.pictures = pictures
        it.videos = videos
        it.tags = tags
    }

    companion object {
        fun create(): SProfile {
            throw Exception("Stub!")
        }
    }

}
