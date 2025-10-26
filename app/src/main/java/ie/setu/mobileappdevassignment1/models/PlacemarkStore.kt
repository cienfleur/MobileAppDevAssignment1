package ie.setu.mobileappdevassignment1.models

interface PlacemarkStore {
    fun findAll(): List<PlacemarkModel>
    fun findOne(id: Long): PlacemarkModel?
    fun indexOf(id: PlacemarkModel): Int
    fun remove(id: PlacemarkModel)
    fun create(placemark: PlacemarkModel)
    fun update(placemark: PlacemarkModel)
    fun size(): Int

}
