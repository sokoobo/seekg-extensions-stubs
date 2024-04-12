@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.model;

class SProfileImpl : SProfile {

    override lateinit var id: String

    override lateinit var url: String

    override lateinit var title: String

    override var description: String? = null

    override var tel: String? = null

    override var thumb: String? = null

    override var city: String? = null

    override var age: Int = 18

    override var pictures: Set<String>? = null

    override var videos: Set<String>? = null

    override var tags: String? = null
}
