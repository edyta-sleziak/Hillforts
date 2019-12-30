//package org.wit.hillforts.models
//
//import org.jetbrains.anko.AnkoLogger
//import org.jetbrains.anko.info
//
//var lastUserId = 0L
//
//internal fun getUserId(): Long {
//  return lastUserId++
//}
//
//class UserMemStore : UserStore, AnkoLogger {
//
//  val users = ArrayList<UserModel>()
//  private var loggedUser: UserModel?
//
//
//
//  override fun findAll(): List<UserModel> {
//    return users
//  }
//
//  override fun findOne(email : String) : UserModel? {
//    var foundUser: UserModel? = users.find { it.email == email}
//    return foundUser
//  }
//
//  override fun create(user: UserModel) {
//    user.id = getUserId()
//    users.add(user)
//  }
//
//
//  override fun update(user: UserModel) {
//    var foundUser: UserModel? = users.find { p -> p.id == user.id }
//    if (foundUser != null) {
//      foundUser.email = user.email
//      foundUser.password = user.password
//    }
//  }
//
//  override fun getLoggedUser(): UserModel? {
//    return loggedUser
//  }
//
//  override fun setLoggedUser(user:UserModel) {
//    loggedUser = user
//  }
//
//}