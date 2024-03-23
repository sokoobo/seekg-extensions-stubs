package source.model

interface SProfile {

    var id: String

    var url: String

    var description: String

    var tel: String

    var title: String

    var thumb: String?

    var city: String

    var age: Int

    /**
     * A list of all pictures urls
     */
    var pictures: Set<String>?

    /**
     * A list of all videos urls
     */
    var videos: Set<String>?

    /**
     * A string containing list of all tags separated with `", "`
     */
    var tags: String?


    companion object {
        fun create(): SProfile {
            throw Exception("Stub!")
        }
    }

}
