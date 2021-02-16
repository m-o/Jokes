package name.ovecka.jokes

import android.util.Log
import android.view.View
import android.widget.ImageView
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
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onDelete: (Int) -> Unit


    override fun bind(holder: EpoxyJokeHolder) {
        super.bind(holder)
        holder.jokeCategory.text = "Category: $jokeCategory"
        holder.jokeText.text = joke

        holder.deleteImage.setOnClickListener {
            Log.d("Deleteeee","delete image")
            onDelete(jokeId)
        }
    }

    inner class EpoxyJokeHolder : EpoxyHolder() {

        lateinit var jokeText: TextView
        lateinit var jokeCategory: TextView
        lateinit var deleteImage: ImageView

        override fun bindView(itemView: View) {
            jokeText = itemView.findViewById(R.id.text)
            jokeCategory = itemView.findViewById(R.id.jokeCategory)
            deleteImage = itemView.findViewById(R.id.deleteImage)
        }

    }
}





