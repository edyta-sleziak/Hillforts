package org.wit.hillforts.models

interface UserStore {

  fun findAll(): List<UserModel>
  fun findOne(email: String) : UserModel?
  fun create(user: UserModel)
  fun update(user: UserModel)
  fun getLoggedUser(): UserModel?
  fun setLoggedUser(user:UserModel ?)
}