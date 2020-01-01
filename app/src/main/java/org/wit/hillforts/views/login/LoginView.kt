package org.wit.hillforts.views.login

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.signup
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.views.BaseView


class LoginView : BaseView(), AnkoLogger {

  lateinit var presenter: LoginPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    progressBar.visibility = View.GONE

    presenter = initPresenter(LoginPresenter(this)) as LoginPresenter


    login.setOnClickListener() {
      val email = email.text.toString()
      val password = password.text.toString()

      if (email.isEmpty() && password.isEmpty()) {
        toast(R.string.enter_credentials)
      } else {
        presenter.doLogin(email, password)
      }
    }

    signup.setOnClickListener() {
      presenter.doSignup()
    }
  }

  override fun showProgress() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideProgress() {
    progressBar.visibility = View.GONE
  }

}
