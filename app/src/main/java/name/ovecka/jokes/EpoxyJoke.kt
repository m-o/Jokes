package name.ovecka.jokes

import android.util.Log
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.joke_layout)
abstract class EpoxyJoke: EpoxyModelWithHolder<EpoxyJoke.EpoxyJokeHolder>() {

    @EpoxyAttribute var jokeId: Int = 0
    @EpoxyAttribute var joke: String = ""
    @EpoxyAttribute var jokeCategory: String = ""


    override fun bind(holder: EpoxyJokeHolder) {
        super.bind(holder)
        holder.jokeId.text = "Joke id: $jokeId"
        holder.jokeCategory.text = "Category: $jokeCategory"
        holder.jokeText.text = joke
    }

    inner class EpoxyJokeHolder : EpoxyHolder() {
        lateinit var jokeId: TextView
        lateinit var jokeText: TextView
        lateinit var jokeCategory: TextView
        override fun bindView(itemView: View) {
            jokeId = itemView.findViewById(R.id.jokeId)
            jokeText = itemView.findViewById(R.id.text)
            jokeCategory = itemView.findViewById(R.id.jokeCategory)
        }
    }
}





