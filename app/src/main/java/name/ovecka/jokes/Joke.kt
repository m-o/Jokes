package name.ovecka.jokes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val joke: String?,
    val category: String,
    val setup: String?,
    val delivery: String?
    )