@file:Suppress("UNUSED")

package com.sokoobo.seekg.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import kotlinx.serialization.serializer
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

inline fun <reified T> Response.parseAs(transform: (String) -> String): T {
    val responseBody = transform(body.string())
    return Injekt.get<Json>().decodeFromString(responseBody)
}

@ExperimentalSerializationApi
inline fun <reified T> Response.parseAs(): T = body.source().use {
    Injekt.get<Json>().decodeFromBufferedSource(serializer(), it)
}

inline fun <reified T> String.parseAs(transform: (String) -> String): T =
    Injekt.get<Json>().decodeFromString(transform(this))

inline fun <reified T> String.parseAs(): T = Injekt.get<Json>().decodeFromString(this)