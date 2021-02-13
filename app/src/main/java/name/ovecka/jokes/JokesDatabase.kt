package name.ovecka.jokes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Joke::class], version = 1, exportSchema = false)
abstract class JokesDatabase: RoomDatabase() {
    abstract fun jokeDao(): DatabaseDao.JokeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: JokesDatabase? = null

        fun getDatabase(context: Context): JokesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        JokesDatabase::class.java,
                        "jokes_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}