package org.wit.hillforts.views.signup

import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.intentFor
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.UserModel
import org.wit.hillforts.models.firebase.HillfortFireStore
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.hillfortslist.HillfortsListView

class SignupPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp
  }
  fun doSignup (user: UserModel) {
    app.users.create(user.copy())
    var newUser = app.users.findOne(user.email)
    if (newUser!!.email.isNotEmpty()) {
      app.users.setLoggedUser(newUser)
      view?.setResult(AppCompatActivity.RESULT_OK)
      view?.finish()
      view?.startActivityForResult(view?.intentFor<HillfortsListView>(),0)
    }
  }

}