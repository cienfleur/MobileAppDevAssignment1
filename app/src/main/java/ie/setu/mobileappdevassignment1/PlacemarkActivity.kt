package ie.setu.mobileappdevassignment1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ie.setu.mobileappdevassignment1.databinding.ActivityPlacemarkBinding
import timber.log.Timber
import timber.log.Timber.i


class PlacemarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlacemarkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPlacemarkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Timber.plant(Timber.DebugTree())
        i("Placemark Activity started..")

        binding.btnAdd.setOnClickListener() {
            i("add Button Pressed")
        }
    }
}