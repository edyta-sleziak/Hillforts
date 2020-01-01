package org.wit.hillforts.views.settings

import org.wit.hillforts.main.MainApp
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW

class SettingsPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp

  }

  fun doChangePassword(new_password: String, old_password: String) {
//    if(app.users.getLoggedUser()!!.email.isNotEmpty()) {
//      if (old_password.equals(app.users.getLoggedUser()!!.password)) {
//        var currentUser = app.users.findOne(app.users.getLoggedUser()!!.email)
//        currentUser!!.password = new_password
//        app.users.update(currentUser)
//        view?.toast("Password changed")
//        view?.navigateTo(VIEW.LIST)
//      } else {
//        view?.toast("Entered old password does not match your current password. Please try again.")
//      }
//    } else {
      view?.navigateTo(VIEW.LOGIN)
 //   }

  }

  fun doCancel() {
    view?.navigateTo(VIEW.LIST)
  }

}