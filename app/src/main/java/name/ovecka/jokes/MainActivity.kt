package name.ovecka.jokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import name.ovecka.jokes.databinding.MainActivityBinding
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigation.setupWithNavController(navController)
    }
}