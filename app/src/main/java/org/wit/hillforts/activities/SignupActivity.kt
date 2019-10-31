package org.wit.hillforts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.*
import org.jetbrains.anko.email
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.models.UserModel


class SignupActivity : AppCompatActivity(), AnkoLogger {

  var user = UserModel()

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signup)

    app = application as MainApp

    signup.setOnClickListener() {
      user.email = email.text.toString()
      user.password = password.text.toString()

      if (user.email.isEmpty() || user.password.isEmpty()) {
        toast(R.string.enter_credentials)
      } else {
        app.users.create(user.copy())
        var newUser = app.users.findOne(user.email)
        if (newUser!!.email.isNotEmpty()) {
          app.users.setLoggedUser(newUser)
          setResult(AppCompatActivity.RESULT_OK)
          finish()
          startActivityForResult(intentFor<HillfortsListActivity>(),0)
        }
      }
    }
  }
}