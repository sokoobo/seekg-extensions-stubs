package source

/**
 * A factory for creating multiple sources at runtime. Use this in case of a source 
 * that supports multiple languages or mirrors of the same website.
 * 
 * **Note:** Your profile source factory classname must be used in the `extClass` param
 * of your `build.gradle` file instead of the true profile source class.
 * 
 * **Example usage:**
 * ```kotlin
 * class SomeSourceFactory : AnimeSourceFactory {
 *     override fun createSources() = listOf(
 *         SomeSource("SomeSource ENG", "en", "https://somesource.en"),
 *         SomeSource("SomeSource ESP", "es", "https://somesource.es"),
 *         SomeSource("SomeSource RUS", "ru", "https://somesource.ru"),
 *     )
 * }
 *
 * class SomeSource(
 *     override val name: String,
 *     override val lang: String, 
 *     override val baseUrl: String,
 * ) : ParsedAnimeHttpSource() {
 *     // some code
 * }
 * ```
 */
interface ProfileSourceFactory {
    /**
     * Create a new copy of the sources
     * @return The created sources
     */
    fun createSources(): List<ProfileSource>
}
