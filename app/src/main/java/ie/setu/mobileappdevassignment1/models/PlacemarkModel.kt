package ie.setu.mobileappdevassignment1.models

import java.sql.Date
import java.util.ArrayList

val placemarks = ArrayList<PlacemarkModel>()

data class PlacemarkModel(
    var id: Int = 0,
    var title: String = "",
    var date: Date = Date(System.currentTimeMillis()),
    var country: String = "",
    var description: String = "")
