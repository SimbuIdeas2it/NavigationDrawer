package com.example.navigationdrawer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.navigationdrawer.db.User
import com.example.navigationdrawer.db.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(app: Application, val repository: UserRepository): AndroidViewModel(app) {
//    private val repository = UserRepository(app)
    fun insert(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
//        AsyncTask.execute {
//            repository.insert(user)
////            viewModelScope.launch {
////                repository.insert(user)
////            }
//        }

//        return repository.insert(user)
    }

    fun update(user: User) {
        repository.update(user)
    }

    fun delete(user: User) {
        repository.delete(user)
    }

    fun checkCredentail(username: String, password: String): LiveData<List<User>> {
        return repository.checkCredentail(username, password)
    }

    fun getAllUsers(): LiveData<List<User>> {
        return repository.getAllUsers()
    }
}