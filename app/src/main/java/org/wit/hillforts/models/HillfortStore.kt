package org.wit.hillforts.models

interface HillfortStore {
  fun findAll(): List<HillfortModel>
  fun create(hillfort: HillfortModel)
  fun update(hillfort: HillfortModel)
  fun delete(hillfort: HillfortModel)
  fun findUsersHillforts(userId: Long): List<HillfortModel>
  fun findVisitedHillforts(): List<HillfortModel>
  fun findFavouriteHillforts(): List<HillfortModel>
  fun findMatch(hillfortName: String): List<HillfortModel>
  fun findById (id: Long) : HillfortModel?
  fun clear()
}