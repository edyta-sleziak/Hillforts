package org.wit.hillforts.room

import android.content.Context
import androidx.room.Room
import org.wit.hillforts.models.HillfortModel
import org.wit.hillforts.models.HillfortStore

class HillfortStoreRoom(val context: Context) : HillfortStore {

  var dao: HillfortDao

  init {
    val database = Room.databaseBuilder(context, Database::class.java, "room.db")
      .fallbackToDestructiveMigration()
      .build()
    dao = database.hillfortDao()
  }

  override fun findAll(): List<HillfortModel> {
    return dao.findAll()
  }

  override fun findById(id: Long): HillfortModel? {
    return dao.findById(id)
  }

  override fun create(hillfort: HillfortModel) {
    dao.create(hillfort)
  }

  override fun update(hillfort: HillfortModel) {
    dao.update(hillfort)
  }

  override fun delete(hillfort: HillfortModel) {
    dao.deleteHillfort(hillfort)
  }

  override fun findUsersHillforts(userId: Long): List<HillfortModel>  {
    return dao.findAddedByUser(userId)
  }

  override fun findVisitedHillforts(userId: Long): List<HillfortModel>  {
    return dao.findVisitedHillforts(userId)
  }

  override fun clear() {
  }
}