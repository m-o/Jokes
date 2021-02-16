package name.ovecka.jokes

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JokeModel(
        val id: Int,
        val type: String,
        val joke: String?,
        val category: String,
        val setup: String?,
        val delivery: String?
){
    companion object{
        enum class JokeType(val value: String) {
            SINGLE("single"),
            TWOPART("twopart")
        }
    }
}