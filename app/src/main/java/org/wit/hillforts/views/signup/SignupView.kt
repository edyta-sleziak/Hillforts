package org.wit.hillforts.views.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.UserModel
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.hillfortslist.HillfortsListView


class SignupView : BaseView(), AnkoLogger {

  var user = UserModel()

  lateinit var presenter: SignupPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signup)

    presenter = initPresenter(SignupPresenter(this)) as SignupPresenter

    signup.setOnClickListener() {
      user.email = email.text.toString()
      user.password = password.text.toString()

      if (user.email.isEmpty() || user.password.isEmpty()) {
        toast(R.string.enter_credentials)
      } else {
        presenter.doSignup(user)
      }
    }
  }
}