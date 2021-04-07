package com.example.navigationdrawer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.example.navigationdrawer.ui.login.Login2Activity
import com.example.navigationdrawer.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar1)
        setSupportActionBar(toolbar)

//        val binding = ActivityMainBinding.inflate(layoutInflater)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        binding.appBar.button.setOnClickListener {
//            val sharedPreference =  getSharedPreferences("demo", Context.MODE_PRIVATE)
//            var editor = sharedPreference.edit()
//            editor.putString("username","username")
//            editor.commit()
//            val intent = Intent(this, Login2Activity::class.java)
//            startActivity(intent)
//        }
//        binding.appBar.button.text = "Logout1"
//
//
//        binding.appBar.button2.text = "User List1"
//        binding.appBar.button2.setOnClickListener {
//            val intent = Intent(this, UsersListActivity::class.java)
//            startActivity(intent)
//        }
        val btn: Button = findViewById(R.id.button)
        btn.text = "Logout"
        btn.setOnClickListener {
            val sharedPreference =  getSharedPreferences("demo", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("username","username")
            editor.commit()
            val intent = Intent(this, Login2Activity::class.java)
            startActivity(intent)
        }

        val btn2: Button = findViewById(R.id.button2)
        btn2.text = "User List"
        btn2.setOnClickListener {
            val intent = Intent(this, UsersListActivity::class.java)
            startActivity(intent)
        }
//        navView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.nav_home -> {
//                    print("Home1")
//                }
//                R.id.nav_gallery -> {
//                    print("Gallery1")
//                }
//                R.id.nav_slideshow -> {
//                    print("Slide1")
//                }
//            }
//            true
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}