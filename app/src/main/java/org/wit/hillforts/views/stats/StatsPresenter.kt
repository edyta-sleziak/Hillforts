package org.wit.hillforts.views.stats

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_stats.*
import org.jetbrains.anko.startActivityForResult
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView
import org.wit.hillforts.views.VIEW
import org.wit.hillforts.views.hillfortslist.HillfortsListView

class StatsPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp
    val user = FirebaseAuth.getInstance().currentUser
    var usersHillforts = app.hillforts.findAll().size
    view.progressBar.max = usersHillforts
    var visitedHillforts = app.hillforts.findVisitedHillforts().size
    view.totalUsersHillforts.text = "$usersHillforts"
    view.totalVisitedHillforts.text = "$visitedHillforts"
    view.progressBar.progress = visitedHillforts
    var favouriteHillforts = app.hillforts.findFavouriteHillforts().size
    view.totalFavouriteHillforts.text = "$favouriteHillforts"
  }

  fun doCancel() {
    view?.navigateTo(VIEW.LIST)
  }

}