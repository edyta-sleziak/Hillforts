package org.wit.hillforts.views.stats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.toolbarOptions
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.models.UserModel

class StatsView : AppCompatActivity(), AnkoLogger {

  lateinit var presenter: StatsPresenter
  var user = UserModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_stats)
    toolbarOptions.title = "Statistics"
    setSupportActionBar(toolbarOptions)

    presenter = StatsPresenter(this)

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
