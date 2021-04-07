package com.example.navigationdrawer.ui.login

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.navigationdrawer.LoginRequestInfo

import com.example.navigationdrawer.R
import com.example.navigationdrawer.RestApiService
import com.example.navigationdrawer.UserViewModel
import com.example.navigationdrawer.db.User

class LoginActivity : AppCompatActivity() {

    private lateinit var vm: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        vm = ViewModelProvider(this)[UserViewModel::class.java]

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        val signUp = findViewById<Button>(R.id.signUp)
        signUp.setOnClickListener {

        }
        login.setOnClickListener {
            val email = username.text.toString()
            val passwd = password.text.toString()
            login(email, passwd)
        }
    }

}

fun login(email: String, password: String) {
    val apiService = RestApiService()
    val userI = LoginRequestInfo(email, password, "")
    apiService.login(userI) {
        if(it?.status == "1") {
            print(it)
//            Toast.makeText(get(), it?.message, Toast.LENGTH_LONG).show()
        }
        else {
//            Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
        }
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}