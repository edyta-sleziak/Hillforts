package org.wit.hillforts.views.favouriteview

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_hillforts_list.*
import org.jetbrains.anko.AnkoLogger
import org.wit.hillforts.R
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW


class FavouritesListView : BaseView(), FavouriteListener, AnkoLogger {

  lateinit var presenter: FavouritesListPresenter

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
    setContentView(R.layout.activity_hillforts_list)

    toolbar.title = "Your favourites"
    setSupportActionBar(toolbar)

    presenter = initPresenter(FavouritesListPresenter(this)) as FavouritesListPresenter

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    presenter.loadHillforts()

    val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
    bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
  }

  override fun showHillforts(hillforts: List<HillfortModel>) {
    recyclerView.adapter = FavouritesAdapter(hillforts, this)
    recyclerView.adapter?.notifyDataSetChanged()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_options, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> presenter.doCancel()
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

