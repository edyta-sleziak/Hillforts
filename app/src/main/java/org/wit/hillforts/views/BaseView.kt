package org.wit.hillforts.views

import android.content.Intent

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger

import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.models.Location
import org.wit.hillforts.views.editlocation.EditLocationView
import org.wit.hillforts.views.favouriteview.FavouritesListView
import org.wit.hillforts.views.hillfortsmaps.HillfortsMapsView
import org.wit.hillforts.views.hillfort.HillfortView
import org.wit.hillforts.views.hillfortslist.HillfortsListView
import org.wit.hillforts.views.login.LoginView
import org.wit.hillforts.views.settings.SettingsView
import org.wit.hillforts.views.signup.SignupView
import org.wit.hillforts.views.splashscreen.SplashScreenView
import org.wit.hillforts.views.stats.StatsView
import org.wit.hillforts.views.search.SearchView

val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
  LOCATION, HILLFORT, MAPS, LIST, STATS, SETTINGS, SPLASHSCREEN, LOGIN, SIGNUP, FAVOURITES, SEARCH
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

  var basePresenter: BasePresenter? = null

  fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
    var intent = Intent(this, HillfortsListView::class.java)
    when (view) {
      VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
      VIEW.HILLFORT -> intent = Intent(this, HillfortView::class.java)
      VIEW.MAPS -> intent = Intent(this, HillfortsMapsView::class.java)
      VIEW.LIST -> intent = Intent(this, HillfortsListView::class.java)
      VIEW.STATS -> intent = Intent(this, StatsView::class.java)
      VIEW.SETTINGS -> intent = Intent(this, SettingsView::class.java)
      VIEW.SPLASHSCREEN -> intent = Intent(this, SplashScreenView::class.java)
      VIEW.LOGIN -> intent = Intent(this, LoginView::class.java)
      VIEW.SIGNUP -> intent = Intent(this, SignupView::class.java)
      VIEW.FAVOURITES -> intent = Intent(this, FavouritesListView::class.java)
      VIEW.SEARCH -> intent = Intent(this, SearchView::class.java)
    }
    if (key != "") {
      intent.putExtra(key, value)
    }
    startActivityForResult(intent, code)
  }

  fun initPresenter(presenter: BasePresenter): BasePresenter {
    basePresenter = presenter
    return presenter
  }

  fun init(toolbar: Toolbar) {
    toolbar.title = title
    setSupportActionBar(toolbar)
    val user = FirebaseAuth.getInstance().currentUser
    if (user != null) {
      toolbar.title = "${title}: ${user.email}"
    }
  }

  override fun onDestroy() {
    basePresenter?.onDestroy()
    super.onDestroy()
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (data != null) {
      basePresenter?.doActivityResult(requestCode, resultCode, data)
    }
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
  }

  open fun showHillfort(hillfort: HillfortModel) {}
  open fun showHillforts(hillforts: List<HillfortModel>) {}
  open fun showLocation(location: Location) {}
  open fun showProgress() {}
  open fun hideProgress() {}
}