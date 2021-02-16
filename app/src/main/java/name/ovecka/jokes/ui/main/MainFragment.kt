package name.ovecka.jokes.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import name.ovecka.jokes.JokeModel
import name.ovecka.jokes.JokeModel.Companion.JokeType
import name.ovecka.jokes.State
import name.ovecka.jokes.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getJokeLiveData().observe(viewLifecycleOwner){jokeState ->

            when(jokeState){
                is State.Success -> {

                    val joke = jokeState.data
                    if(joke.type == JokeType.SINGLE.value){
                        binding.message.text = joke.joke
                    }
                    else{
                        binding.message.text = "${joke.setup}\n \n ${joke.delivery}"
                    }

                    setupJokeClickListener(joke)
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), jokeState.error, Toast.LENGTH_SHORT).show()
                }
            }

        }

        viewModel.getSaveJokeLiveData().observe(viewLifecycleOwner){jokeState ->
            when(jokeState){
                is State.Success -> {
                    if (jokeState.data) {
                        Toast.makeText(requireContext(), "Joke saved!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "There was a problem saving your joke :( !", Toast.LENGTH_SHORT).show()
                    }
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), jokeState.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.newJokeButton.setOnClickListener {
            viewModel.getRandomJoke()
        }
    }

    private fun setupJokeClickListener(joke: JokeModel){
        binding.saveJokeButton.setOnClickListener {
            if(viewModel.currentJokeSaved){
                Toast.makeText(requireContext(),"This Joke is already saved. Try next one.",Toast.LENGTH_SHORT).show()
            }
            else {
                viewModel.saveJoke(joke)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}