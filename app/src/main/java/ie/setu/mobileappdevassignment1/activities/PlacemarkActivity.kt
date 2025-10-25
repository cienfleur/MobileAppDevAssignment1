package ie.setu.mobileappdevassignment1.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import ie.setu.mobileappdevassignment1.R
import ie.setu.mobileappdevassignment1.models.PlacemarkModel
import ie.setu.mobileappdevassignment1.databinding.ActivityPlacemarkBinding
import ie.setu.mobileappdevassignment1.main.MainApp
import timber.log.Timber
import timber.log.Timber.i

class PlacemarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlacemarkBinding
    lateinit var app: MainApp
    var placemark = PlacemarkModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        app = application as MainApp
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAdd.setOnClickListener() {
            placemark.title = binding.placemarkTitle.text.toString()
            placemark.description = binding.placemarkDesc.text.toString()
            if (placemark.title.isNotEmpty()) {
                Timber.i("placemark added: title: ${placemark.title}, description: ${placemark.description}")
                app.placemarks.add(placemark.copy())
                for (i in app.placemarks.indices) {
                    Timber.i("placemark[$i]: ${app.placemarks[i]}")
                }
            } else {
                Snackbar

                    .make(it, "Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnAdd.setOnClickListener {
            placemark.title = binding.placemarkTitle.text.toString()
            placemark.description = binding.placemarkDesc.text.toString()
            if (placemark.title.isNotEmpty()) {
                i("add Button Pressed: ${placemark.title}")
                app.placemarks.add(placemark.copy())
                for (i in app.placemarks.indices) {
                    i("placemark[$i]: ${app.placemarks[i]}")
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar

                    .make(it, "Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}