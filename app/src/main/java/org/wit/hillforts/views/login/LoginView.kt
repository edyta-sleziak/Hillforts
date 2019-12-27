package org.wit.hillforts.views.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.signup
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.models.UserModel
import org.wit.hillforts.views.BaseView


class LoginView : BaseView(), AnkoLogger {

  var user = UserModel()
  lateinit var presenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    presenter = initPresenter(LoginPresenter(this)) as LoginPresenter


    login.setOnClickListener() {
      user.email = email.text.toString()
      user.password = password.text.toString()

      if (user.email.isEmpty() && user.password.isEmpty()) {
        toast(R.string.enter_credentials)
      } else {
        presenter.doLogin(user)
      }
    }

    signup.setOnClickListener() {
      presenter.doSignup()
    }
  }

}
