package org.wit.hillforts.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.hillforts.helpers.*
import java.util.*

val USER_FILE= "users.json"
val gsonBuilderUsers = GsonBuilder().setPrettyPrinting().create()
val listTypeUser = object : TypeToken<java.util.ArrayList<UserModel>>() {}.type



fun setRandomId(): Long {
  return Random().nextLong()
}

class UserJSONStore : UserStore, AnkoLogger {

  val context: Context
  var users = mutableListOf<UserModel>()
  private var loggedUser: UserModel?

  constructor (context: Context) {
    this.context = context
    this.loggedUser = null
    if (exists(context, USER_FILE)) {
      deserialize()
    }
  }

  override fun findAll(): MutableList<UserModel> {
    return users
  }

  override fun findOne(email: String): UserModel? {
    var foundUser: UserModel? = users.find { u -> u.email == email }
    return foundUser
  }

  override fun create(user: UserModel) {
    user.id = setRandomId()
    users.add(user)
    serialize()
  }


  override fun update(user: UserModel) {
    var foundUser: UserModel? = users.find { u -> u.id == user.id }
    if (foundUser != null) {
      foundUser.email = user.email
      foundUser.password = user.password
      serialize()
    }
  }

  override fun getLoggedUser(): UserModel? {
    return loggedUser
  }

  override fun setLoggedUser(user:UserModel?) {
    loggedUser = user
  }

  private fun serialize() {
    val jsonString = gsonBuilderUsers.toJson(users, listTypeUser)
    write(context, USER_FILE, jsonString)
  }

  private fun deserialize() {
    val jsonString = read(context, USER_FILE)
    users = Gson().fromJson(jsonString, listTypeUser)
  }

}