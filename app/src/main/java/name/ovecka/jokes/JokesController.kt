package name.ovecka.jokes

import android.util.Log
import com.airbnb.epoxy.EpoxyController

class JokesController : EpoxyController() {

    private val jokesList = mutableListOf<JokeModel>()

    fun setJokes(jokesList: List<JokeModel>){
        this.jokesList.clear()
        this.jokesList.addAll(jokesList)
        requestModelBuild()
    }

    override fun buildModels() {
        jokesList.forEach {joke->
            Log.d("JokesController",joke.toString())

//            if(joke.type == JokeModel.Companion.JokeType.SINGLE.value){
//                EpoxyJoke.
//            }
//            else{
//                JokeEpoxyModel_(joke.setup + "\n 'n " + joke.delivery)
//            }
        }

    }

}