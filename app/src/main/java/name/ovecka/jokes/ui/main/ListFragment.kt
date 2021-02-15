package name.ovecka.jokes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import name.ovecka.jokes.databinding.ListFragmentBinding
import name.ovecka.jokes.*

class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    private val controller by lazy{
        JokesController()
    }

    lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.getAllJokes()
        viewModel.getJokeListLiveData().observe(viewLifecycleOwner){jokeList ->

            binding.recyclerView.withModels {
                jokeList.sortedByDescending { it.id }.forEach { jokeModel ->
                    if(jokeModel.type == JokeModel.Companion.JokeType.SINGLE.value){
                        epoxyJoke{
                            id(jokeModel.id)
                            jokeId(jokeModel.id)
                            jokeCategory(jokeModel.category)
                            joke(jokeModel.joke!!)
                        }
                    }
                    else{
                        epoxyJoke{
                            id(jokeModel.id)
                            jokeId(jokeModel.id)
                            jokeCategory(jokeModel.category)
                            joke(jokeModel.setup!! + "\n \n" + jokeModel.delivery)
                        }
                    }
                }
            }
        }
    }
}