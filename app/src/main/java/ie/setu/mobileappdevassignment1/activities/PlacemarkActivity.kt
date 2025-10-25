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
import timber.log.Timber

class PlacemarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlacemarkBinding

    val placemarks = ArrayList<PlacemarkModel>()

    var placemark = PlacemarkModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
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
                placemarks.add(placemark.copy())
                Timber.i("placemarks List: $placemarks")

            }
            else {
                Snackbar

                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }
}