package org.wit.hillforts.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.activity_hillfort.HillfortDescription
import kotlinx.android.synthetic.main.activity_hillfort.HillfortName
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.helpers.readImage
import org.wit.hillforts.helpers.readImageFromPath
import org.wit.hillforts.helpers.showImagePicker
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.models.Location
import org.wit.hillforts.models.UserModel

class SettingsActivity : AppCompatActivity(), AnkoLogger {

  var user = UserModel()
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    toolbarOptions.title = "Settings"
    setSupportActionBar(toolbarOptions)

    app = application as MainApp

    change_password.setOnClickListener {
      if (old_password.text.toString().isEmpty()) {
        toast("Your old password is required")
      } else {
        if(new_password.text.toString().isEmpty()) {
          toast("New password is required")
        } else {
          if(app.users.getLoggedUser()!!.email.isNotEmpty()) {
            if (old_password.text.toString().equals(app.users.getLoggedUser()!!.password)) {
              var currentUser = app.users.findOne(app.users.getLoggedUser()!!.email)
              currentUser!!.password = new_password.text.toString()
              app.users.update(currentUser)
              toast("Password changed")
              startActivityForResult<HillfortsListActivity>(0)
            } else {
              toast("Entered old password does not match your current password. Please try again.")
            }
          } else {
            startActivityForResult<LoginActivity>(0)
          }
        }
      }
    }

  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> startActivityForResult<HillfortsListActivity>(0)
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_options, menu)
    return super.onCreateOptionsMenu(menu)
  }




}

