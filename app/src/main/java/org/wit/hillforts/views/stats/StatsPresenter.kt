package org.wit.hillforts.views.stats

import kotlinx.android.synthetic.main.activity_stats.*
import org.jetbrains.anko.startActivityForResult
import org.wit.hillforts.views.hillfortslist.HillfortsListView
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.views.BasePresenter
import org.wit.hillforts.views.BaseView

class StatsPresenter(view: BaseView) : BasePresenter(view) {

  init {
    app = view.application as MainApp
    //var usersHillforts = app.hillforts.findUsersHillforts(app.users.getLoggedUser()!!.id).size
   // view.progressBar.max = usersHillforts
   // var visitedHillforts = app.hillforts.findVisitedHillforts(app.users.getLoggedUser()!!.id).size
    //view.totalUsersHillforts.text = "$usersHillforts"
    //view.totalVisitedHillforts.text = "$visitedHillforts"
    //view.progressBar.progress = visitedHillforts
  }

  fun doCancel() {
    view?.startActivityForResult<HillfortsListView>(0)
  }

}