package org.wit.hillforts.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.email
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_login.signup
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.models.UserModel


class LoginActivity : AppCompatActivity(), AnkoLogger {

  var user = UserModel()
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    app = application as MainApp


    login.setOnClickListener() {
      user.email = email.text.toString()
      user.password = password.text.toString()

      if (user.email.isEmpty() && user.password.isEmpty()) {
        toast(R.string.enter_credentials)
      } else {
        var seekedUser = app.users.findOne(user.email)
        if(seekedUser!!.email.isNotEmpty()) {                                                     //todo bug when user is not recognised
          if(seekedUser.password == user.password) {
            app.users.setLoggedUser(seekedUser)
            setResult(AppCompatActivity.RESULT_OK)
            finish()
            startActivityForResult(intentFor<HillfortsListActivity>(),0)
          } else {
            toast(R.string.toast_incorrectpassword)
          }
        } else {
          toast(R.string.toast_incorrectpassword)
        }
      }
    }

    signup.setOnClickListener() {
      startActivityForResult(intentFor<SignupActivity>(),0)
    }
  }



}
