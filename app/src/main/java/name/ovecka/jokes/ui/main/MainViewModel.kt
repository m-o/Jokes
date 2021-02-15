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
    private var saveJokeLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var currentJokeSaved = false

    fun getJokeLiveData() = jokeModelLiveData as LiveData<JokeModel>
    fun getSaveJokeLiveData() = saveJokeLiveData as LiveData<Boolean>

    fun getRandomJoke(){
        repository.getRandomJoke()
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onNext = {
                    jokeModelLiveData.postValue(it)
                    currentJokeSaved = false
                         },
                onError =  { it.printStackTrace() },
                onComplete = { println("Done!") }
        )
    }

    fun saveJoke(joke: JokeModel){
            repository.insertJoke(joke).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
                onComplete = {
                    saveJokeLiveData.postValue(true)
                    currentJokeSaved = true
                             },
                onError =  { saveJokeLiveData.postValue(false)})
    }

}