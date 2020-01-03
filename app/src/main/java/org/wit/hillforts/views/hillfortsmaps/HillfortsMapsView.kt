package org.wit.hillforts.views.hillfortsmaps

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.wit.hillforts.R
import kotlinx.android.synthetic.main.activity_hillfrots_maps.toolbarOptions
import kotlinx.android.synthetic.main.content_hillfrots_maps.*
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW


class HillfortsMapsView : BaseView(), GoogleMap.OnMarkerClickListener {

  lateinit var presenter: HillfortMapPresenter
  lateinit var map: GoogleMap

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.navigation_favourites -> {
        presenter.view?.navigateTo(VIEW.FAVOURITES)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_home -> {
        presenter.view?.navigateTo(VIEW.LIST)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_map -> {
        presenter.view?.navigateTo(VIEW.MAPS)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfrots_maps)
    toolbarOptions.title = "Map"
    setSupportActionBar(toolbarOptions)

    presenter = initPresenter(HillfortMapPresenter(this)) as HillfortMapPresenter

    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync {
      map = it
      map.setOnMarkerClickListener(this)
      presenter.loadHillforts()
    }

    val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
    bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> presenter.doCancel()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }

  override fun onMarkerClick(marker: Marker): Boolean {
    presenter.doMarkerSelected(marker)
    return true
  }

  override fun showHillfort(hillfort: HillfortModel) {
    currentName.text = hillfort.name
    currentDescription.text = hillfort.description
    ratingBarMap.rating = hillfort.rating.toFloat()
    Glide.with(this).load(hillfort.image).into(currentImage)
  }

  override fun showHillforts(hillforts: List<HillfortModel>) {
    presenter.doPopulateMap(map, hillforts)
  }

}
