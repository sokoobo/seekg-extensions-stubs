@file:Suppress("unused_parameter")

package com.sokoobo.seekg.source

import com.sokoobo.seekg.source.model.Video
import com.sokoobo.seekg.source.model.Picture
import com.sokoobo.seekg.source.model.SProfile

interface ProfileSource {

    val id: Long
    val name: String
    val versionId: Int

    suspend fun getProfileDetails(profile: SProfile): SProfile
    suspend fun getPictureList(profile: SProfile): List<Picture>
    suspend fun getVideoList(profile: SProfile): List<Video>
}