package name.ovecka.jokes

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


interface JokesApi {

    @GET("joke/Any?safe-mode")
    fun randomJoke(): Observable<JokeModel>

}