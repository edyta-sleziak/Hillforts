package org.wit.hillforts.room

import androidx.room.*
import org.wit.hillforts.models.HillfortModel

@Dao
interface HillfortDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun create(hillfort: HillfortModel)

  @Query("select * from HillfortModel")
  fun findAll(): List<HillfortModel>

  @Query("select * from HillfortModel where id = :id")
  fun findById(id: Long): HillfortModel

  @Query("select * from HillfortModel where userId = :userId and visited = 'true'")
  fun findVisitedHillforts(userId: Long): List<HillfortModel>

  @Query("select * from HillfortModel where userId = :userId")
  fun findAddedByUser(userId: Long): List<HillfortModel>

  @Update
  fun update(hillfort: HillfortModel)

  @Delete
  fun deleteHillfort(hillfort: HillfortModel)
}