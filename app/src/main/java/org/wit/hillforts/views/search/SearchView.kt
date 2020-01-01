package org.wit.hillforts.views.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.*
import org.wit.hillforts.R
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.views.BaseView


class SearchView : BaseView(), SearchListener, AnkoLogger {

  lateinit var presenter: SearchPresenter

  internal var textlength = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)

    toolbarOptions.title = "Search"
    setSupportActionBar(toolbarOptions)

    presenter = initPresenter(SearchPresenter(this)) as SearchPresenter
    val layoutManager = LinearLayoutManager(this)

    searchPhrase.addTextChangedListener(object: TextWatcher {
      override fun afterTextChanged(s: Editable) {}

      override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        textlength = searchPhrase.text.length
        var searchedHillfort = searchPhrase.text.toString()

        recyclerViewSearch.layoutManager = layoutManager
        presenter.doSearchFor(searchedHillfort)
      }
    })
  }

  override fun showHillforts(hillforts: List<HillfortModel>) {
    recyclerViewSearch.adapter = SearchAdapter(hillforts, this)
    recyclerViewSearch.adapter?.notifyDataSetChanged()
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
    recyclerViewSearch.adapter?.notifyDataSetChanged()
    super.onActivityResult(requestCode, resultCode, data)
  }

}