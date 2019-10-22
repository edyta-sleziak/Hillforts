package org.wit.hillforts.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillforts.models.HillfortMemStore

class MainApp : Application(), AnkoLogger {

  var hillforts = HillfortMemStore()

  override fun onCreate() {
    super.onCreate()
    info("Application started")

  }
}