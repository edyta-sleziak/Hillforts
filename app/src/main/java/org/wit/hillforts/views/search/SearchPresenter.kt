package org.wit.hillforts.views.search


import org.jetbrains.anko.doAsync
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.uiThread
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW
import org.wit.hillforts.views.hillfort.HillfortView

class SearchPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp

  }

  fun doCancel() {
    view?.navigateTo(VIEW.LIST)
  }

  fun doEditHillfort(hillfort: HillfortModel) {
    view?.startActivityForResult(view?.intentFor<HillfortView>()!!.putExtra("hillfort_edit", hillfort), 0)
  }

  fun doSearchFor(searchedHillfort: String) {
    doAsync {
      val hillforts = app.hillforts.findMatch(searchedHillfort)
      uiThread {
        view?.showHillforts(hillforts)
      }
    }
  }

}