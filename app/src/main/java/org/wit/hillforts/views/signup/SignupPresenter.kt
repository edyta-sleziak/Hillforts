package org.wit.hillforts.views.signup

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.toast
import org.wit.hillforts.models.firebase.HillfortFireStore
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW

class SignupPresenter(view: BaseView) : BasePresenter(view) {

  var auth: FirebaseAuth = FirebaseAuth.getInstance()
  var fireStore: HillfortFireStore? = null

  init {
    if (app.hillforts is HillfortFireStore) {
      fireStore = app.hillforts as HillfortFireStore
    }
  }

  fun doSignup(email: String, password: String) {
    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(view!!) { task ->
      if (task.isSuccessful) {
        fireStore!!.fetchHillforts {
          view?.navigateTo(VIEW.LIST)
        }
      } else {
        view?.toast("Sign Up Failed: ${task.exception?.message}")
      }
    }
  }
}