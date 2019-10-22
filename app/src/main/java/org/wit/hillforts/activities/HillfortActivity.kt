package org.wit.hillforts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillforts.R
import org.wit.hillforts.models.HillfortModel

class HillfortActivity : AppCompatActivity(), AnkoLogger {

  var hillfort = HillfortModel()
  val hillforts = ArrayList<HillfortModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)
    info("Hillfort acrivity started")

    btnAdd.setOnClickListener() {
      val hillfortName = HillfortName.text.toString()
      val hillfortDescription = HillfortDescription.text.toString()
      if (hillfortName.isNotEmpty()) {
        info("Add Button Pressed: $hillfort")
        hillfort.name = hillfortName
        hillfort.description = hillfortDescription
        hillforts.add(hillfort.copy())
        info("$hillforts")
      }
      else {
        toast ("Please enter all details")
      }
    }
  }
}
