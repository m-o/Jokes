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

class ListViewModel : ViewModel() {

    private val repository = JokesApplication().repository
    private var jokeListLiveData: MutableLiveData<State<List<JokeModel>>> = MutableLiveData()

    fun getJokeListLiveData() = jokeListLiveData as LiveData<State<List<JokeModel>>>

    init {
        getAllJokes()
    }

    //Intentionally not sending info to fragment.
    fun deleteJoke(joke: JokeModel): LiveData<State<Boolean>>{
        val deleteJokeLiveData = MutableLiveData<State<Boolean>>()
        repository.deleteJoke(joke).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onComplete = {
                deleteJokeLiveData.postValue(State.Success(true))
            },
            onError = {
                deleteJokeLiveData.postValue(State.Error(it.message ?: "No message, still error :)"))
            }
        )
        return deleteJokeLiveData
    }

    fun getAllJokes(){
        repository.allJokes.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = {
                    val jokesList = it.map { joke -> JokeModel( id = joke.id, type = joke.type, joke = joke.joke, category= joke.category, setup= joke.setup, delivery = joke.delivery)}
                    jokeListLiveData.postValue(State.Success(jokesList))},
                onError = {
                    jokeListLiveData.postValue(State.Error(it.message ?: "No message, still error :)"))
                }
        )
    }
}