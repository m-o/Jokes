package name.ovecka.jokes.ui.main

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import name.ovecka.jokes.*
import name.ovecka.jokes.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val itemDecor = DividerItemDecoration(requireContext(), HORIZONTAL)
        binding.recyclerView.addItemDecoration(itemDecor)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.getAllJokes()
        viewModel.getJokeListLiveData().observe(viewLifecycleOwner){ jokeListState ->

            when(jokeListState){

                is State.Success ->{
                    setupRecyclerView(jokeListState.data)
                }

                is State.Error -> {
                    Toast.makeText(requireContext(),jokeListState.error, Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    private fun setupRecyclerView(jokeList: List<JokeModel>){

        binding.recyclerView.withModels {
            jokeList.sortedByDescending { it.id }.forEach { jokeModel ->

                val jokeText: String = if(jokeModel.type == JokeModel.Companion.JokeType.SINGLE.value){
                    jokeModel.joke!!
                } else{
                    jokeModel.setup!! + "\n " + jokeModel.delivery
                }

                epoxyJoke{
                    id(jokeModel.id)
                    jokeId(jokeModel.id)
                    jokeCategory(jokeModel.category)
                    joke(jokeText)
                    onDelete{
                        deleteJoke(jokeModel)
                    }
                }

            }
        }
    }

    private fun deleteJoke(joke: JokeModel){
        viewModel.deleteJoke(joke)
    }
}