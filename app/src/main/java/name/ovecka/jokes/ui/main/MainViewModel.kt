package name.ovecka.jokes.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import name.ovecka.jokes.*

class MainViewModel : ViewModel() {

    private val repository = JokesApplication().repository
    private var jokeModelLiveData: MutableLiveData<JokeModel> = MutableLiveData()
    private var jokeListLiveData: MutableLiveData<List<JokeModel>> = MutableLiveData()

    fun getJokeLiveData() = jokeModelLiveData as LiveData<JokeModel>
    fun getJokeListLiveData() = jokeListLiveData as LiveData<List<JokeModel>>

    fun getRandomJoke(){
        repository.getRandomJoke()
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = { jokeModelLiveData.postValue(it)},
                onError =  { it.printStackTrace() },
                onComplete = { println("Done!") }
        )
    }

    fun saveJoke(joke: JokeModel){
            repository.insertJoke(joke).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onComplete = { Log.d("MainViewModel","joke inserted")},
                onError =  { it.printStackTrace() })
    }

    fun getAllJokes(){
        repository.allJokes.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = {
                    val jokesList = it.map { joke -> JokeModel( id = joke.id, type = joke.type, joke = joke.joke, category= joke.category, setup= joke.setup, delivery = joke.delivery)}
                    jokeListLiveData.postValue(jokesList)},
                onError =  { it.printStackTrace()},
                onComplete = { println("Done!")}
        )
    }
}