package org.wit.hillforts.views

import android.content.Intent
import org.wit.hillforts.main.MainApp

open class BasePresenter(var view: BaseView?) {

  var app: MainApp =  view?.application as MainApp

  open fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
  }

  open fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
  }

  open fun onDestroy() {
    view = null
  }
}