package org.wit.hillforts.views.hillfort

import android.content.Intent
import org.jetbrains.anko.intentFor
import org.wit.hillforts.helpers.showImagePicker
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.Location
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.editlocation.EditLocationView

class HillfortPresenter(val view: HillfortView) {

  val IMAGE_REQUEST = 1
  val LOCATION_REQUEST = 2

  var hillfort = HillfortModel()
  var location = Location(52.245696, -7.139102, 15f)
  var app: MainApp
  var edit = false

  init {
    app = view.application as MainApp
    if (view.intent.hasExtra("hillfort_edit")) {
      edit = true
      hillfort = view.intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
      view.showHillfort(hillfort)
    }
  }

  fun doAddOrSave(name: String, description: String, notes: String, visited: Boolean, visitedDate: String, userId: Long) {
    hillfort.name = name
    hillfort.description = description
    hillfort.notes = notes
    hillfort.visited = visited
    hillfort.visitedDate = visitedDate
    hillfort.userId = userId
    if (edit) {
      app.hillforts.update(hillfort)
    } else {
      app.hillforts.create(hillfort)
    }
    view.finish()
  }

  fun doCancel() {
    view.finish()
  }

  fun doDelete() {
    app.hillforts.delete(hillfort)
    view.finish()
  }

  fun doSelectImage() {
    showImagePicker(view, IMAGE_REQUEST)
  }

  fun doSetLocation() {
    if (hillfort.zoom != 0f) {
      location.lat = hillfort.lat
      location.lng = hillfort.lng
      location.zoom = hillfort.zoom
    }
    view.startActivityForResult(view.intentFor<EditLocationView>().putExtra("location", location), LOCATION_REQUEST)
  }

  fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    when (requestCode) {
      IMAGE_REQUEST -> {
        hillfort.image = data.data.toString()
        view.showHillfort(hillfort)
      }
      LOCATION_REQUEST -> {
        location = data.extras?.getParcelable<Location>("location")!!
        hillfort.lat = location.lat
        hillfort.lng = location.lng
        hillfort.zoom = location.zoom
      }
    }
  }
}