package ie.setu.mobileappdevassignment1.main

import android.app.Application
import ie.setu.mobileappdevassignment1.models.PlacemarkJSONStore
import ie.setu.mobileappdevassignment1.models.PlacemarkModel
import ie.setu.mobileappdevassignment1.models.PlacemarkStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var placemarks: PlacemarkStore


    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        placemarks = PlacemarkJSONStore(applicationContext)
        i("Placemark started")
    }
}