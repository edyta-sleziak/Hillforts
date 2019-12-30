package org.wit.hillforts.views.hillfortslist

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_hillforts_list.*
import org.jetbrains.anko.AnkoLogger
import org.wit.hillforts.R
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.hillfortslist.HillfortAdapter
import org.wit.hillforts.views.hillfortslist.HillfortListener
import org.wit.hillforts.views.hillfortslist.HillfortsListPresenter


class HillfortsListView : BaseView(), HillfortListener, AnkoLogger {

  lateinit var presenter: HillfortsListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillforts_list)


    toolbar.title = title
    setSupportActionBar(toolbar)

    presenter = initPresenter(HillfortsListPresenter(this)) as HillfortsListPresenter

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadHillforts()
  }

  override fun showHillforts(hillforts: List<HillfortModel>) {
    recyclerView.adapter = HillfortAdapter(hillforts, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> presenter.doAddHillfort()
      R.id.item_stats -> presenter.doShowStats()
      R.id.item_settings -> presenter.doShowSettings()
      R.id.item_map -> presenter.doShowHillfortsMap()
      R.id.item_logout -> presenter.doLogout()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onHillfortClick(hillfort: HillfortModel) {
    presenter.doEditHillfort(hillfort)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    recyclerView.adapter?.notifyDataSetChanged()
    super.onActivityResult(requestCode, resultCode, data)
  }

}

