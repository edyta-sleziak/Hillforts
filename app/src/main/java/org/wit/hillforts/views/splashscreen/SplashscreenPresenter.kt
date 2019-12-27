package org.wit.hillforts.views.splashscreen

import org.wit.hillforts.main.MainApp
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView

class SplashscreenPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp
  }

}