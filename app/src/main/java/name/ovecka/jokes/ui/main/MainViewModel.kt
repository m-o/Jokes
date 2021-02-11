package name.ovecka.jokes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import name.ovecka.jokes.Joke
import name.ovecka.jokes.JokesRepository
import name.ovecka.jokes.JokesService

class MainViewModel : ViewModel() {
    private val jokesApi = JokesService().getJokesApi()
    private val repository = JokesRepository(jokesApi)
    private var jokeLiveData: MutableLiveData<Joke> = MutableLiveData()
    fun getJokeLiveData() = jokeLiveData as LiveData<Joke>

    fun getRandomJoke(){
        repository.getRandomJoke()
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = { jokeLiveData.postValue(it)},
                onError =  { it.printStackTrace() },
                onComplete = { println("Done!") }
        )
    }
}