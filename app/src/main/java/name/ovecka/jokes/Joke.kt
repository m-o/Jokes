package name.ovecka.jokes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(var type: String, var joke: String?, var category: String, var setup: String?, var delivery: String?){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}