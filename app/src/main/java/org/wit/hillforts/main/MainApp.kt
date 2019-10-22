package org.wit.hillforts.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillforts.models.HillfortModel

class MainApp : Application(), AnkoLogger {

  var hillforts = ArrayList<HillfortModel>()

  override fun onCreate() {
    super.onCreate()
    info("Application started")

    hillforts.add(HillfortModel("Hillfort One", "About hillfort one..."))
    hillforts.add(HillfortModel("Hillfort Two", "About hillfort two..."))
    hillforts.add(HillfortModel("Hillfort Three", "About hillfort three..."))
    hillforts.add(HillfortModel("Hillfort Four", "About hillfort four..."))
    hillforts.add(HillfortModel("Hillfort Five", "About hillfort five..."))
    hillforts.add(HillfortModel("Hillfort Six", "About hillfort six..."))
  }
}