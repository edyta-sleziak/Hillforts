package org.wit.hillforts.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.activity_hillfort.HillfortDescription
import kotlinx.android.synthetic.main.activity_hillfort.HillfortName
import kotlinx.android.synthetic.main.activity_stats.*
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.helpers.readImage
import org.wit.hillforts.helpers.readImageFromPath
import org.wit.hillforts.helpers.showImagePicker
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.models.Location
import java.util.*

class HillfortActivity : AppCompatActivity(), AnkoLogger {

  var hillfort = HillfortModel()
  val IMAGE_REQUEST = 1
  val LOCATION_REQUEST = 2
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)
    toolbarHillfort.title = title
    setSupportActionBar(toolbarOptions)
    chooseImage.setOnClickListener {
      info ("Select image")
    }
    val datepicker = Calendar.getInstance()
    val year = datepicker.get(Calendar.YEAR)
    val month = datepicker.get(Calendar.MONTH)
    val day = datepicker.get(Calendar.DAY_OF_MONTH )

    app = application as MainApp

    var edit = false

    chooseImage.setOnClickListener {
      showImagePicker(this, IMAGE_REQUEST)
    }

    HillfortVisitedDate.setOnClickListener {
      val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { DatePicker, year, month, dayOfMonth ->
        HillfortVisitedDate.setText("$dayOfMonth / $month / $year")
      }, day, month, year)
      datePickerDialog.show()
      var date = datePickerDialog.toString()
      hillfort.visitedDate = date
    }

    hillfortLocation.setOnClickListener {
      val location = Location(52.245696, -7.139102, 15f)
      if (hillfort.zoom != 0f) {
        location.lat =  hillfort.lat
        location.lng = hillfort.lng
        location.zoom = hillfort.zoom
      }
      startActivityForResult(intentFor<MapActivity>().putExtra("location", location), LOCATION_REQUEST)
    }

    if (intent.hasExtra("hillfort_edit")) {
      edit = true
      hillfort = intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
      HillfortName.setText(hillfort.name)
      HillfortDescription.setText(hillfort.description)
      HillfortNotes.setText(hillfort.notes)
      HillfortVisited.setChecked(hillfort.visited)
      HillfortImage.setImageBitmap(readImageFromPath(this, hillfort.image))
      if(hillfort.image != null) {
        chooseImage.setText(R.string.button_updateImage)
      }
      btnAdd.setText(R.string.button_editHillfort)
    }

    btnAdd.setOnClickListener() {
      hillfort.name = HillfortName.text.toString()
      hillfort.description = HillfortDescription.text.toString()
      hillfort.notes = HillfortNotes.text.toString()
      hillfort.visited = HillfortVisited.isChecked
      hillfort.userId = app.users.getLoggedUser()!!.id
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
      R.id.item_delete -> {
        app.hillforts.delete(hillfort)
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
          chooseImage.setText(R.string.button_updateImage)
        }
      }
      LOCATION_REQUEST -> {
        if(data != null) {
          val location = data.extras?.getParcelable<Location>("location")!!
          hillfort.lat = location.lat
          hillfort.lng = location.lng
          hillfort.zoom = location.zoom
        }
      }
    }
  }


}
