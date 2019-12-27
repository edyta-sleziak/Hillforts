package org.wit.hillforts.views.login

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.UserModel
import org.wit.hillforts.views.hillfortslist.HillfortsListView
import org.wit.hillforts.views.signup.SignupView

class LoginPresenter(val view: LoginView) {

  var app: MainApp

  init {
    app = view.application as MainApp
  }

  fun doLogin (user: UserModel) {
    var seekedUser = app.users.findOne(user.email)
    if(seekedUser!!.email.isNotEmpty()) {                                                     //todo bug when user is not recognised
      if(seekedUser.password == user.password) {
        app.users.setLoggedUser(seekedUser)
        view.setResult(AppCompatActivity.RESULT_OK)
        view.finish()
        view.startActivityForResult(view.intentFor<HillfortsListView>(),0)
      } else {
        view.toast(R.string.toast_incorrectpassword)
      }
    } else {
      view.toast(R.string.toast_incorrectpassword)
    }
  }

  fun doSignup () {
    view.startActivityForResult(view.intentFor<SignupView>(),0)
  }

}