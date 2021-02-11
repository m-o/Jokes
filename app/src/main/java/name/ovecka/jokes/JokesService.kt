package name.ovecka.jokes

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class JokesService {

    private var jokesApi: JokesApi? = null

    fun getJokesApi(): JokesApi {
        return if(jokesApi == null) {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://v2.jokeapi.dev/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            jokesApi = retrofit.create(JokesApi::class.java)
            jokesApi!!
        }
        else{
            jokesApi!!
        }
    }

}