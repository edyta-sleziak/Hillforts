package org.wit.hillforts.views.login

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.UserModel
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW
import org.wit.hillforts.views.hillfortslist.HillfortsListView
import org.wit.hillforts.views.signup.SignupView

class LoginPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp
  }

  var auth: FirebaseAuth = FirebaseAuth.getInstance()

  fun doLogin(email: String, password: String) {
    view?.showProgress()
    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        view?.navigateTo(VIEW.LIST)
      } else {
        view?.toast("Sign Up Failed: ${task.exception?.message}")
      }
      view?.hideProgress()
    }
  }

  fun doSignup () {
    view?.navigateTo(VIEW.SIGNUP)
  }

}