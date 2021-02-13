package name.ovecka.jokes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class DatabaseDao {

    @Dao
    interface JokeDao {

        @Query("SELECT * FROM Joke")
        fun getAllJokes(): Flowable<List<Joke>>

        @Insert
        fun insert(joke: Joke): Completable

        @Query("DELETE FROM Joke")
        fun deleteAll(): Completable
    }

}