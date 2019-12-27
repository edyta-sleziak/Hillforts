package org.wit.hillforts.views.hillfortslist

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.hillfort.HillfortView
import org.wit.hillforts.views.hillfortsmaps.HillfortsMapsView
import org.wit.hillforts.views.login.LoginView
import org.wit.hillforts.views.settings.SettingsView
import org.wit.hillforts.views.stats.StatsView

class HillfortsListPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp
  }

  fun getHillforts() = app.hillforts.findAll()

  fun doAddHillfort() {
    view?.startActivityForResult<HillfortView>(0)
  }

  fun doEditHillfort(hillfort: HillfortModel) {
    view?.startActivityForResult(view?.intentFor<HillfortView>()!!.putExtra("hillfort_edit", hillfort), 0)
  }

  fun doShowHillfortsMap() {
    view?.startActivity<HillfortsMapsView>()
  }

  fun doShowStats() {
    view?.startActivity<StatsView>()
  }

  fun doShowSettings() {
    view?.startActivity<SettingsView>()
  }

  fun doLogout() {
    app.users.setLoggedUser(null)
    view?.startActivity<LoginView>()
  }
}