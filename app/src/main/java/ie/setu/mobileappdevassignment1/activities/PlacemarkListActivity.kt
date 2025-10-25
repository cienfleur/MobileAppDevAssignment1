package ie.setu.mobileappdevassignment1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.mobileappdevassignment1.main.MainApp
import ie.setu.mobileappdevassignment1.models.PlacemarkModel
import ie.setu.mobileappdevassignment1.databinding.ActivityPlacemarkListBinding
import ie.setu.mobileappdevassignment1.adapters.PlacemarkAdapter
import ie.setu.mobileappdevassignment1.R



interface PlacemarkListener {
    fun onPlacemarkClick(placemark: PlacemarkModel)
    fun onPlacemarkDeleteClick(placemark: PlacemarkModel)
}

class PlacemarkListActivity : AppCompatActivity(), PlacemarkListener {


    lateinit var app: MainApp
    private lateinit var binding: ActivityPlacemarkListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = PlacemarkAdapter(app.placemarks, this)
    }

    override fun onPlacemarkDeleteClick(placemark: PlacemarkModel) {
        app.placemarks.remove(placemark)
        binding.recyclerView.adapter?.notifyItemRemoved(app.placemarks.indexOf(placemark))
    }

    override fun onPlacemarkClick(placemark: PlacemarkModel) {
        val launcherIntent = Intent(this, PlacemarkActivity::class.java)
        getResult.launch(launcherIntent)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, PlacemarkActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.placemarks.size)
            }
        }
}