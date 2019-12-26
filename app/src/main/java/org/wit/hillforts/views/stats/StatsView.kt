package org.wit.hillforts.views.stats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.toolbarOptions
import kotlinx.android.synthetic.main.activity_stats.*
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.UserModel
import org.wit.hillforts.views.hillfortslist.HillfortsListView

class StatsView : AppCompatActivity(), AnkoLogger {

  var user = UserModel()
  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_stats)
    toolbarOptions.title = "Statistics"
    setSupportActionBar(toolbarOptions)

    app = application as MainApp

    var usersHillforts = app.hillforts.findUsersHillforts(app.users.getLoggedUser()!!.id).size
    progressBar.max = usersHillforts
    var visitedHillforts = app.hillforts.findVisitedHillforts(app.users.getLoggedUser()!!.id).size
    totalUsersHillforts.text = "$usersHillforts"
    totalVisitedHillforts.text = "$visitedHillforts"
    progressBar.progress = visitedHillforts


  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> startActivityForResult<HillfortsListView>(0)
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_options, menu)
    return super.onCreateOptionsMenu(menu)
  }



}
