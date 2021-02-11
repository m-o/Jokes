package name.ovecka.jokes


class JokesRepository(val api: JokesApi) {
    fun getRandomJoke() = api.randomJoke()
}