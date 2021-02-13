package name.ovecka.jokes

import android.app.Application
import android.content.Context

class JokesApplication : Application() {
    val database by lazy { JokesDatabase.getDatabase(appContext) }
    val jokesApi by lazy { JokesService().getJokesApi()}
    val repository by lazy { JokesRepository(jokesApi,database.jokeDao()) }

    override fun onCreate() {
        super.onCreate()
        JokesApplication.appContext = applicationContext
    }

    companion object {
        lateinit  var appContext: Context
    }
}