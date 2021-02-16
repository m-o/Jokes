package name.ovecka.jokes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import name.ovecka.jokes.*

class MainViewModel : ViewModel() {

    private val repository = JokesApplication().repository
    private var jokeModelLiveData: MutableLiveData<State<JokeModel>> = MutableLiveData()
    private var saveJokeLiveData: MutableLiveData<State<Boolean>> = MutableLiveData()

    var currentJokeSaved = false

    fun getJokeLiveData() = jokeModelLiveData as LiveData<State<JokeModel>>
    fun getSaveJokeLiveData() = saveJokeLiveData as LiveData<State<Boolean>>

    init {
        if(jokeModelLiveData.value == null){
            getRandomJoke()
        }
    }

    fun getRandomJoke(){
        repository.getRandomJoke()
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = {
                    jokeModelLiveData.postValue(State.Success(it))
                    currentJokeSaved = false
                },
                onError =  {
                    jokeModelLiveData.postValue(State.Error(it.message ?: "No message, still error :)"))
                })
    }

    fun saveJoke(joke: JokeModel){
            repository.insertJoke(joke).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onComplete = {
                    saveJokeLiveData.postValue(State.Success(true))
                    currentJokeSaved = true
                },
                onError = {
                    saveJokeLiveData.postValue(State.Error(it.message ?: "No message, still error :)"))
                })
    }

}