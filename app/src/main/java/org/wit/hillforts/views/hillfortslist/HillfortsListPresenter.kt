package org.wit.hillforts.views.hillfortslist

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.*
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW
import org.wit.hillforts.views.hillfort.HillfortView
import org.wit.hillforts.views.hillfortsmaps.HillfortsMapsView
import org.wit.hillforts.views.login.LoginView
import org.wit.hillforts.views.settings.SettingsView
import org.wit.hillforts.views.stats.StatsView

class HillfortsListPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp
  }

  fun loadHillforts() {
    doAsync {
      val hillforts = app.hillforts.findAll()
      uiThread {
        view?.showHillforts(hillforts)
      }
    }
  }

  fun doAddHillfort() {
    view?.navigateTo(VIEW.HILLFORT)
  }

  fun doEditHillfort(hillfort: HillfortModel) {
    view?.startActivityForResult(view?.intentFor<HillfortView>()!!.putExtra("hillfort_edit", hillfort), 0)
  }

  fun doShowHillfortsMap() {
    view?.navigateTo(VIEW.MAPS)
  }

  fun doShowStats() {
    view?.navigateTo(VIEW.STATS)
  }

  fun doShowSettings() {
    view?.navigateTo(VIEW.SETTINGS)
  }

  fun doShowSearch() {
    view?.navigateTo(VIEW.SEARCH)
  }

  fun doShowFavourites() {
    view?.navigateTo(VIEW.FAVOURITES)
  }

  fun doLogout() {
    FirebaseAuth.getInstance().signOut()
    app.hillforts.clear()
    view?.navigateTo(VIEW.LOGIN)
  }
}