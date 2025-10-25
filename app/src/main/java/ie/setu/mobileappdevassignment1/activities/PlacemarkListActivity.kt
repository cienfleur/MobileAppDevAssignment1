package ie.setu.mobileappdevassignment1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.setu.mobileappdevassignment1.R
import ie.setu.mobileappdevassignment1.main.MainApp

class PlacemarkListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placemark_list)
        app = application as MainApp
    }
}
