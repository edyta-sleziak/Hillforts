package org.wit.hillforts.views.settings

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW

class SettingsPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp


  }

  val user = FirebaseAuth.getInstance().currentUser

  fun doChangePassword(new_password: String) {
    user?.updatePassword(new_password)
    view?.toast("Password changed")
  }

  fun doChangeEmail(new_email: String) {
    user?.updateEmail(new_email)
    view?.toast("Email changed")
  }


  fun doCancel() {
    view?.navigateTo(VIEW.LIST)
  }

}