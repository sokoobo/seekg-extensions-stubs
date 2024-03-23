package source.model

data class ProfileFilterList(val list: List<ProfileFilter<*>>) : List<ProfileFilter<*>> by list {
    constructor(vararg fs: ProfileFilter<*>) : this(if (fs.isNotEmpty()) fs.asList() else emptyList())
}
