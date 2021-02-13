package name.ovecka.jokes

import android.util.Log
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable


class JokesRepository(val api: JokesApi, val jokeDao: DatabaseDao.JokeDao) {

    val allJokes: Flowable<List<Joke>> = jokeDao.getAllJokes()

    fun insertJoke(jokeModel: JokeModel): Completable{
        val joke = Joke(type = jokeModel.type, joke = jokeModel.joke, category = jokeModel.category, setup = jokeModel.setup, delivery = jokeModel.delivery)
        return jokeDao.insert(joke)
    }

    fun getRandomJoke() = api.randomJoke()
}