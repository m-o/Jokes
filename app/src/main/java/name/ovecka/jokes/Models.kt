package name.ovecka.jokes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Joke(
        val type: String,
        val joke: String?,
        val category: String,
        val setup: String?,
        val delivery: String?
)