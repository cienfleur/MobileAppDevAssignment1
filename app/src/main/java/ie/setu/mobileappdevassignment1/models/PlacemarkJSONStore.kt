package ie.setu.mobileappdevassignment1.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import ie.setu.mobileappdevassignment1.helpers.*
import timber.log.Timber
import java.util.*

private const val JSON_FILE = "placemarks.json"

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PlacemarkJSONStore(private val context: Context) : PlacemarkStore {

    var placemarks = mutableListOf<PlacemarkModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PlacemarkModel> {
        logAll()
        return placemarks
    }

    override fun findOne(id: Long) : PlacemarkModel? {
        return placemarks.find { p -> p.id == id }
    }

    override fun indexOf(id: PlacemarkModel): Int {
        return placemarks.indexOf(id)
    }

    override fun remove(id: PlacemarkModel) {
        placemarks.remove(id)
        serialize()
    }

    override fun create(placemark: PlacemarkModel) {
        placemark.id = generateRandomId()
        placemarks.add(placemark)
        serialize()
    }

    override fun update(placemark: PlacemarkModel) {
        val foundPlacemark = findOne(placemark.id)
        if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
            foundPlacemark.country = placemark.country
            foundPlacemark.date = placemark.date
            serialize()
        }
    }

    internal fun logAll() {
        placemarks.forEach { Timber.i("$it") }
    }

    override fun size(): Int {
        return placemarks.size
    }

    private fun serialize() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val listType: Type = object : TypeToken<ArrayList<PlacemarkModel>>() {}.type
        val jsonString = gson.toJson(placemarks, listType)
        write(context, JSON_FILE, jsonString)
        Timber.i("JSON Serialized and Written to File")
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        if (jsonString.isNotEmpty()) {
            val gson = Gson()
            val listType: Type = object : TypeToken<ArrayList<PlacemarkModel>>() {}.type
            placemarks = gson.fromJson(jsonString, listType)
            Timber.i("JSON Deserialized and Loaded from File")
        }
    }
}
