package name.ovecka.jokes.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import name.ovecka.jokes.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getRandomJoke()
        viewModel.getAllJokes()

        viewModel.getJokeLiveData().observe(viewLifecycleOwner){joke ->
            binding.message.text = joke.joke
        }

        viewModel.getJokeListLiveData().observe(viewLifecycleOwner){jokes ->
            jokes.forEach { joke ->
                Log.d("JokeFragment",joke.toString())
            }
        }

        binding.newJokeButton.setOnClickListener {
            viewModel.getRandomJoke()
        }

        binding.saveJokeButton.setOnClickListener {
            viewModel.getJokeLiveData().value?.let {
                viewModel.saveJoke(it)
            }
        }

        binding.getAllJokesButton.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}