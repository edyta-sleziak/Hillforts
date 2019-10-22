package org.wit.hillforts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel

class HillfortActivity : AppCompatActivity(), AnkoLogger {

  var hillfort = HillfortModel()
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)
    app = application as MainApp

    toolbarHillfort.title = title
    setSupportActionBar(toolbarHillfort)

    btnAdd.setOnClickListener() {
      hillfort.name = HillfortName.text.toString()
      hillfort.description = HillfortDescription.text.toString()
      if (hillfort.name.isNotEmpty()) {
        app.hillforts.add(hillfort.copy())
        info("Add Button Pressed: $hillfort")
        for (i in app.hillforts.indices) {
          info("Hillfort[$i]:${app.hillforts[i]}")
        }
      setResult(AppCompatActivity.RESULT_OK)
      finish()
      } else {
        toast ("Please enter all details")
      }
    }

  }
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_hillfort, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> {
        finish()
      }
    }
    return super.onOptionsItemSelected(item)
  }
}
