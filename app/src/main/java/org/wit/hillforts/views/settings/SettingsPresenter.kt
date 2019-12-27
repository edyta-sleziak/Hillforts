package org.wit.hillforts.views.settings

import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.hillfortslist.HillfortsListView
import org.wit.hillforts.views.login.LoginView

class SettingsPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp

  }

  fun doChangePassword(new_password: String, old_password: String) {
    if(app.users.getLoggedUser()!!.email.isNotEmpty()) {
      if (old_password.equals(app.users.getLoggedUser()!!.password)) {
        var currentUser = app.users.findOne(app.users.getLoggedUser()!!.email)
        currentUser!!.password = new_password
        app.users.update(currentUser)
        view?.toast("Password changed")
        view?.startActivityForResult<HillfortsListView>(0)
      } else {
        view?.toast("Entered old password does not match your current password. Please try again.")
      }
    } else {
      view?.startActivityForResult<LoginView>(0)
    }

  }

  fun doCancel() {
    view?.startActivityForResult<HillfortsListView>(0)
  }

}