package org.wit.hillforts.views.hillfortsmaps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.hillforts.R
import kotlinx.android.synthetic.main.activity_hillfrots_maps.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.content_hillfrots_maps.*
import org.wit.hillforts.helpers.readImageFromPath
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BaseView


class HillfortsMapsView : BaseView(), GoogleMap.OnMarkerClickListener {

  lateinit var presenter: HillfortMapPresenter
  lateinit var map: GoogleMap

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfrots_maps)
    toolbar.title = title
    setSupportActionBar(toolbarOptions)

    presenter = initPresenter(HillfortMapPresenter(this)) as HillfortMapPresenter

    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync {
      map = it
      map.setOnMarkerClickListener(this)
      presenter.loadHillforts()
    }
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
    Glide.with(this).load(hillfort.image).into(currentImage)
  }

  override fun showHillforts(hillforts: List<HillfortModel>) {
    presenter.doPopulateMap(map, hillforts)
  }

}
