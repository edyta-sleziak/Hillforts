package org.wit.hillforts.views.hillfort

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.activity_hillfort.HillfortDescription
import kotlinx.android.synthetic.main.activity_hillfort.HillfortName
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.models.Location
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW
import java.util.*


class HillfortView : BaseView(), AnkoLogger {

  lateinit var presenter: HillfortPresenter
  lateinit var map: GoogleMap
  var hillfort = HillfortModel()

  lateinit var app: MainApp

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.navigation_favourites -> {
        presenter.view?.navigateTo(VIEW.FAVOURITES)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_home -> {
        presenter.view?.navigateTo(VIEW.LIST)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_map -> {
        presenter.view?.navigateTo(VIEW.MAPS)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)
    toolbarHillfort.title = title
    setSupportActionBar(toolbarHillfort)
    chooseImage.setOnClickListener {
      info ("Select image")
    }
    presenter = initPresenter(HillfortPresenter(this)) as HillfortPresenter
    val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)

    val datepicker = Calendar.getInstance()
    val year = datepicker.get(Calendar.YEAR)
    val month = datepicker.get(Calendar.MONTH)
    val day = datepicker.get(Calendar.DAY_OF_MONTH )


    app = application as MainApp

    chooseImage.setOnClickListener {
      presenter.doSelectImage()
    }

    HillfortVisitedDate.setOnClickListener {
      val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { DatePicker, year, month, dayOfMonth ->
        HillfortVisitedDate.setText("$dayOfMonth / "+ ((month)+1) +" / $year")
      }, year, month, day)
      datePickerDialog.show()
    }

    hillfortMap.getMapAsync {
      presenter.doConfigureMap(it)
      it.setOnMapClickListener { presenter.doSetLocation() }
    }

    hillfortMap.onCreate(savedInstanceState)
    hillfortMap.getMapAsync {
      map = it
      presenter.doConfigureMap(map)
    }

    bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

  }

  override fun showHillfort(hillfort: HillfortModel) {
    HillfortName.setText(hillfort.name)
    HillfortDescription.setText(hillfort.description)
    HillfortNotes.setText(hillfort.notes)
    HillfortVisited.setChecked(hillfort.visited)
    HillfortVisitedDate.setText(hillfort.visitedDate)
    Favourite.setChecked(hillfort.favourite)
    ratingBar.setRating(hillfort.rating.toFloat())
    Glide.with(this).load(hillfort.image).into(HillfortImage)
    if(hillfort.image != null) {
      chooseImage.setText(R.string.button_updateImage)
    }
    lat.setText("%.4f".format(hillfort.location.lat))
    lng.setText("%.4f".format(hillfort.location.lng))
  }

  override fun showLocation(location: Location) {
    lat.setText("%.4f".format(location.lat))
    lng.setText("%.4f".format(location.lng))
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_hillfort, menu)
    if (presenter.edit)
      menu.getItem(0).setVisible(true)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> {
        presenter.doCancel()
      }
      R.id.item_delete -> {
        presenter.doDelete()
      }
      R.id.item_save -> {
        if (HillfortName.text.toString().isEmpty()) {
          toast(R.string.enter_name)
        } else {
          presenter.doAddOrSave(HillfortName.text.toString(),
            HillfortDescription.text.toString(),
            HillfortNotes.text.toString(),
            HillfortVisited.isChecked,
            HillfortVisitedDate.text.toString(),
            ratingBar.rating.toInt(),
            Favourite.isChecked
          )
        }
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if(data != null) {
      presenter.doActivityResult(requestCode, resultCode, data)
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    hillfortMap.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    hillfortMap.onLowMemory()
  }

  override fun onPause() {
    super.onPause()
    hillfortMap.onPause()
  }

  override fun onResume() {
    super.onResume()
    hillfortMap.onResume()
    presenter.doResartLocationUpdates()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    hillfortMap.onSaveInstanceState(outState)
  }


}
