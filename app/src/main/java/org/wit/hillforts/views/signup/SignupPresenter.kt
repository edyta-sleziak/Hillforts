package org.wit.hillforts.views.signup

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.UserModel
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW
import org.wit.hillforts.views.hillfortslist.HillfortsListView

class SignupPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp
  }

  var auth: FirebaseAuth = FirebaseAuth.getInstance()

  fun doSignup(email: String, password: String) {
    //view?.showProgress()
    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        view?.navigateTo(VIEW.LIST)
      } else {
        view?.toast("Sign Up Failed: ${task.exception?.message}")
      }
      //view?.hideProgress()
    }
  }

}