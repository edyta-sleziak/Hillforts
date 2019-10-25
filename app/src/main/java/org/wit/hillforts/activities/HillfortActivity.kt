package org.wit.hillforts.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.activity_hillfort.HillfortDescription
import kotlinx.android.synthetic.main.activity_hillfort.HillfortName
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillforts.R
import org.wit.hillforts.helpers.readImage
import org.wit.hillforts.helpers.readImageFromPath
import org.wit.hillforts.helpers.showImagePicker
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel

class HillfortActivity : AppCompatActivity(), AnkoLogger {

  var hillfort = HillfortModel()
  val IMAGE_REQUEST = 1
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)
    toolbarHillfort.title = title
    setSupportActionBar(toolbarHillfort)
    chooseImage.setOnClickListener {
      info ("Select image")
    }

    app = application as MainApp

    var edit = false

    chooseImage.setOnClickListener {
      showImagePicker(this, IMAGE_REQUEST)
    }

    if (intent.hasExtra("hillfort_edit")) {
      edit = true
      hillfort = intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
      HillfortName.setText(hillfort.name)
      HillfortDescription.setText(hillfort.description)
      HillfortImage.setImageBitmap(readImageFromPath(this, hillfort.image))
      btnAdd.setText(R.string.button_editHillfort)
    }

    btnAdd.setOnClickListener() {
      hillfort.name = HillfortName.text.toString()
      hillfort.description = HillfortDescription.text.toString()
      if (hillfort.name.isEmpty()) {
        toast(R.string.enter_name)
      } else {
        if(edit) {
          app.hillforts.update(hillfort.copy())
        } else {
          app.hillforts.create(hillfort.copy())
        }
        setResult(AppCompatActivity.RESULT_OK)
        finish()
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

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when (requestCode) {
      IMAGE_REQUEST -> {
        if (data != null) {
          hillfort.image = data.getData().toString()
          HillfortImage.setImageBitmap(readImage(this, resultCode, data))
        }
      }
    }
  }


}
