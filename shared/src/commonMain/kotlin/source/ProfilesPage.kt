package source

import source.model.SProfile

data class ProfilesPage(val profiles: List<SProfile>, val hasNextPage: Boolean)
