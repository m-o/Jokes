package name.ovecka.jokes

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class JokesRepository(val api: JokesApi, val jokeDao: DatabaseDao.JokeDao) {

    val allJokes: Flowable<List<Joke>> = jokeDao.getAllJokes()

    fun insertJoke(jokeModel: JokeModel): Completable{
        with(jokeModel) {
            val joke = Joke(id = id, type = type, joke = joke, category = category, setup = setup, delivery = delivery)
            return jokeDao.insert(joke)
        }
    }

    fun getRandomJoke() = api.randomJoke()

    fun deleteJoke(jokeModel: JokeModel): Completable{
        with(jokeModel){
            val joke = Joke(id = id, type = type, joke = joke, category = category, setup = setup, delivery = delivery)
            return jokeDao.deleteJoke(joke)
        }
    }
}