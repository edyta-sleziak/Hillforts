package org.wit.hillforts.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillforts.models.json.HillfortJSONStore
import org.wit.hillforts.models.HillfortStore
import org.wit.hillforts.models.json.UserJSONStore
import org.wit.hillforts.models.UserStore
import org.wit.hillforts.models.firebase.HillfortFireStore
import org.wit.hillforts.room.HillfortStoreRoom

class MainApp : Application(), AnkoLogger {

  lateinit var hillforts: HillfortStore
  lateinit var users: UserStore

  override fun onCreate() {
    super.onCreate()
    //hillforts = HillfortStoreRoom(applicationContext)
    //hillforts = HillfortJSONStore(applicationContext)
    //users = UserJSONStore(applicationContext)
    hillforts = HillfortFireStore(applicationContext)
    info("Application started")

  }
}