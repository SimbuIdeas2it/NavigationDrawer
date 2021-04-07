package com.example.navigationdrawer.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.huawei.todolist.utils.subscribeOnBackground
import javax.inject.Inject


class UserRepository @Inject constructor(application: Application, private val userDao1: UserDao) {
    private var userDao: UserDao
    private val database = UserDataBase.getInstance(application)
//    @Inject val database = UserDataBase
    init {
        userDao = database.userDao()
    }

    fun insert(note: User) {
        userDao.insert(note)
    }

    fun update(note: User) {
        subscribeOnBackground {
            userDao.update(note)
        }

    }

    fun delete(note: User) {
        subscribeOnBackground {
            userDao.delete(note)
        }
    }

    fun checkCredentail(username: String, password: String): LiveData<List<User>> {

        return userDao.checkCredentail(username = username, password = password)
    }

    fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }
}