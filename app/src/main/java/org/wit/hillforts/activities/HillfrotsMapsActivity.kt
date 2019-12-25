package org.wit.hillforts.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import org.wit.hillforts.R
import kotlinx.android.synthetic.main.activity_hillfrots_maps.*
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.content_hillfrots_maps.*
import org.wit.hillforts.main.MainApp


class HillfrotsMapsActivity : AppCompatActivity() {
  lateinit var app: MainApp
  lateinit var map: GoogleMap

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfrots_maps)
    toolbar.title = title
    setSupportActionBar(toolbar)
    mapView.onCreate(savedInstanceState)
    app = application as MainApp
    mapView.getMapAsync {
      map = it
      configureMap()
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

  fun configureMap() {
    map.uiSettings.setZoomControlsEnabled(true)
  }

}
