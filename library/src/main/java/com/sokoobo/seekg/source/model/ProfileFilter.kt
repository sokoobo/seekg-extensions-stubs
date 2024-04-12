@file:Suppress("UNUSED")

package com.sokoobo.seekg.source.model

sealed class ProfileFilter<T>(val name: String, var state: T) {
    open class Header(name: String) : ProfileFilter<Any>(name, 0)
    open class Separator(name: String = "") : ProfileFilter<Any>(name, 0)

    abstract class Select<V>(name: String, val values: Array<V>, state: Int = 0) :
        ProfileFilter<Int>(name, state)

    abstract class Text(name: String, state: String = "") : ProfileFilter<String>(name, state)
    abstract class CheckBox(name: String, state: Boolean = false) :
        ProfileFilter<Boolean>(name, state)

    abstract class TriState(name: String, state: Int = STATE_IGNORE) :
        ProfileFilter<Int>(name, state) {
        fun isIgnored() = state == STATE_IGNORE
        fun isIncluded() = state == STATE_INCLUDE
        fun isExcluded() = state == STATE_EXCLUDE

        companion object {
            const val STATE_IGNORE = 0
            const val STATE_INCLUDE = 1
            const val STATE_EXCLUDE = 2
        }
    }

    abstract class Group<V>(name: String, state: List<V>) : ProfileFilter<List<V>>(name, state)
    abstract class Sort(name: String, val values: Array<String>, state: Selection? = null) :
        ProfileFilter<Sort.Selection?>(name, state) {
        data class Selection(val index: Int, val ascending: Boolean)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProfileFilter<*>) return false

        return name == other.name && state == other.state
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (state?.hashCode() ?: 0)
        return result
    }
}
