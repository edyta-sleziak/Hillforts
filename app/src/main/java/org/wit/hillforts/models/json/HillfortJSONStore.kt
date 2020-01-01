//package org.wit.hillforts.models.json
//
//import android.content.Context
//import com.google.gson.Gson
//import com.google.gson.GsonBuilder
//import com.google.gson.reflect.TypeToken
//import org.jetbrains.anko.AnkoLogger
//import org.wit.hillforts.helpers.*
//import org.wit.hillforts.models.HillfortModel
//import org.wit.hillforts.models.HillfortStore
//import java.util.*
//
//val JSON_FILE = "hillforts.json"
//val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
//val listType = object : TypeToken<java.util.ArrayList<HillfortModel>>() {}.type
//
//fun generateRandomId(): Long {
//  return Random().nextLong()
//}
//
//class HillfortJSONStore : HillfortStore, AnkoLogger {
//
//  val context: Context
//  var hillforts = mutableListOf<HillfortModel>()
//
//  constructor (context: Context) {
//    this.context = context
//    if (exists(context, JSON_FILE)) {
//      deserialize()
//    }
//  }
//
//  override fun findAll(): MutableList<HillfortModel> {
//    return hillforts
//  }
//
//  override fun findFavouriteHillforts(): MutableList<HillfortModel> {
//    return hillforts
//  }
//
//  override fun findUsersHillforts(userId: Long): List<HillfortModel> {
//    return hillforts.filter { it.userId == userId }
//  }
//
//  override fun findVisitedHillforts(userId: Long): List<HillfortModel> {
//    var allHillforts =  hillforts.filter { it.userId == userId }
//    return allHillforts.filter { it.visited == true }
//  }
//
//  override fun create(hillfort: HillfortModel) {
//    hillfort.id = generateRandomId()
//    hillforts.add(hillfort)
//    serialize()
//  }
//
//
//  override fun update(hillfort: HillfortModel) {
//    var foundHillfort: HillfortModel? = hillforts.find { p -> p.id == hillfort.id }
//    if (foundHillfort != null) {
//      foundHillfort.name = hillfort.name
//      foundHillfort.description = hillfort.description
//      foundHillfort.notes = hillfort.notes
//      foundHillfort.image = hillfort.image
//      foundHillfort.location = hillfort.location
//      foundHillfort.visited = hillfort.visited
//      foundHillfort.visitedDate = hillfort.visitedDate
//      foundHillfort.userId = hillfort.userId
//      serialize()
//    }
//  }
//
//  private fun serialize() {
//    val jsonString = gsonBuilder.toJson(hillforts,
//      listType
//    )
//    write(context, JSON_FILE, jsonString)
//  }
//
//  private fun deserialize() {
//    val jsonString = read(context, JSON_FILE)
//    hillforts = Gson().fromJson(jsonString, listType)
//  }
//
//  override fun delete(hillfort: HillfortModel) {
//    hillforts.remove(hillfort)
//    serialize()
//  }
//
//  override fun findById(id: Long): HillfortModel? {
//    val foundHillfort: HillfortModel? = hillforts.find { it.id == id }
//    return foundHillfort
//  }
//
//  override fun clear() {
//    hillforts.clear()
//  }
//}
//
