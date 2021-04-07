package com.example.navigationdrawer.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigationdrawer.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
    }
}