package org.wit.hillforts.views.splashscreen

import org.wit.hillforts.main.MainApp

class SplashscreenPresenter(val view: SplashScreenView) {

  var app: MainApp

  init {
    app = view.application as MainApp
  }

}