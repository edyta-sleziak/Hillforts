package org.wit.hillforts.views.signup

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.views.BaseView


class SignupView : BaseView(), AnkoLogger {


  lateinit var presenter: SignupPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signup)

    presenter = initPresenter(SignupPresenter(this)) as SignupPresenter

    signup.setOnClickListener() {
      val email = email.text.toString()
      val password = password.text.toString()

      if (email.isEmpty() || password.isEmpty()) {
        toast(R.string.enter_credentials)
      } else {
        presenter.doSignup(email, password)
      }
    }
  }
}