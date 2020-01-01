package org.wit.hillforts.views.settings

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.toolbarOptions
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.views.BaseView

class SettingsView : BaseView(), AnkoLogger {

  lateinit var presenter: SettingsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_settings)
    toolbarOptions.title = "Settings"
    setSupportActionBar(toolbarOptions)

    presenter = initPresenter(SettingsPresenter(this)) as SettingsPresenter

    change_email.setOnClickListener {
      if (new_email.text.toString().isEmpty()) {
        toast("New email is required")
      } else {
        presenter.doChangeEmail(new_email.text.toString())
      }
    }

    change_password.setOnClickListener {
      if (new_password.text.toString().isEmpty()) {
        toast("New password is required")
      } else {
        presenter.doChangePassword(new_password.text.toString())
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> presenter.doCancel()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_options, menu)
    return super.onCreateOptionsMenu(menu)
  }

}

