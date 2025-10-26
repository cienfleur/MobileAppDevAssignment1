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
import java.util.Calendar
import timber.log.Timber.i
import android.content.Intent
import android.widget.ArrayAdapter
import android.app.DatePickerDialog

class PlacemarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlacemarkBinding
    lateinit var app: MainApp
    var placemark = PlacemarkModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val countries = resources.getStringArray(R.array.countries_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countries)
        binding.placemarkCountry.setAdapter(adapter)
        binding.placemarkDate.setOnClickListener {
            showDatePickerDialog()
        }
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAdd.setOnClickListener {
            placemark.title = binding.placemarkTitle.text.toString()
            placemark.description = binding.placemarkDesc.text.toString()
            placemark.country = binding.placemarkCountry.text.toString()
            placemark.date = binding.placemarkDate.text.toString()
            if (placemark.title.isNotEmpty()) {
                i("add Button Pressed: ${placemark.title}")
                app.placemarks.create(placemark.copy())
                for (i in app.placemarks.findAll()) {
                    i("placemark[$i]: ${app.placemarks}")
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar

                    .make(it, "Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.placemarkDate.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}