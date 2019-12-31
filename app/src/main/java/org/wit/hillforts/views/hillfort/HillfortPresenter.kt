package org.wit.hillforts.views.hillfort

import android.content.Intent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.uiThread
import org.wit.hillforts.helpers.showImagePicker
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.Location
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW
import org.wit.hillforts.views.editlocation.EditLocationView

class HillfortPresenter(view: BaseView) : BasePresenter(view) {

  val IMAGE_REQUEST = 1
  val LOCATION_REQUEST = 2

  var map: GoogleMap? = null
  var hillfort = HillfortModel()
  var location = Location(52.245696, -7.139102, 15f)
  var edit = false

  init {
    app = view.application as MainApp
    if (view.intent.hasExtra("hillfort_edit")) {
      edit = true
      hillfort = view.intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
      view.showHillfort(hillfort)
    }
  }

  fun doAddOrSave(name: String, description: String, notes: String, visited: Boolean, visitedDate: String, rating: Int, isFavoutite: Boolean) {
    hillfort.name = name
    hillfort.description = description
    hillfort.notes = notes
    hillfort.visited = visited
    hillfort.visitedDate = visitedDate
    hillfort.favourite = isFavoutite
    hillfort.rating = rating
    doAsync {
      if (edit) {
        app.hillforts.update(hillfort)
      } else {
        app.hillforts.create(hillfort)
      }
      uiThread {
        view?.finish()
      }
    }
  }

  fun doCancel() {
    view?.finish()
  }

  fun doDelete() {
    doAsync {
      app.hillforts.delete(hillfort)
      uiThread {
        view?.finish()
      }
    }
  }

  fun doSelectImage() {
    showImagePicker(view!!, IMAGE_REQUEST)
  }

  fun doConfigureMap(m: GoogleMap) {
    map = m
    locationUpdate(hillfort.location)
  }

  fun doSetLocation() {
    view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", Location(hillfort.location.lat, hillfort.location.lng, hillfort.location.zoom))
  }

  fun locationUpdate(location: Location) {
    hillfort.location = location
    hillfort.location.zoom = 15f
    map?.clear()
    val options = MarkerOptions().title(hillfort.name).position(LatLng(hillfort.location.lat, hillfort.location.lng))
    map?.addMarker(options)
    map?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(hillfort.location.lat, hillfort.location.lng), hillfort.location.zoom))
    view?.showLocation(hillfort.location)
  }

  override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    when (requestCode) {
      IMAGE_REQUEST -> {
        hillfort.image = data.data.toString()
        view?.showHillfort(hillfort)
      }
      LOCATION_REQUEST -> {
        location = data.extras?.getParcelable<Location>("location")!!
        hillfort.location = location
        locationUpdate(location)
      }
    }
  }
}