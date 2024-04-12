@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.model

data class ProfileFilterList(val list: List<ProfileFilter<*>>) : List<ProfileFilter<*>> by list {
    constructor(vararg fs: ProfileFilter<*>) : this(if (fs.isNotEmpty()) fs.asList() else emptyList())

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return list.hashCode()
    }
}
