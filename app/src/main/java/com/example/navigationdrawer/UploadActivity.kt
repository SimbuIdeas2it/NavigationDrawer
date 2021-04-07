package com.example.navigationdrawer

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class UploadActivity : AppCompatActivity() {
    lateinit var pickPhotosButton: Button
    companion object {
        private const val GALLERY_REQUEST_CODE = 300
        private const val PERMISSIONS_REQUEST_CODE = 301

        private const val MAX_NUMBER_REQUEST_PERMISSIONS = 2

        private const val IMAGE_TYPE = "image/*"
        private const val IMAGE_CHOOSER_TITLE = "Select Picture"

        private val PERMISSIONS = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private var permissionRequestCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        pickPhotosButton = findViewById<Button>(R.id.pickPhotosButton)
        pickPhotosButton.isEnabled = true
        requestPermissionsIfNecessary()

        pickPhotosButton.setOnClickListener {
            list()
            showPhotoPicker()
        }
    }

    private fun showPhotoPicker() {
        val intent = Intent().apply {
            type = IMAGE_TYPE
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            action = Intent.ACTION_OPEN_DOCUMENT
        }

        startActivityForResult(Intent.createChooser(intent, IMAGE_CHOOSER_TITLE), GALLERY_REQUEST_CODE)
    }

    private fun requestPermissionsIfNecessary() {
        if (!hasRequiredPermissions()) {
            askForPermissions()
        }
    }

    private fun askForPermissions() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSIONS_REQUEST_CODE)
//        if (permissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
//            permissionRequestCount += 1
//
//            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSIONS_REQUEST_CODE)
//        } else {
//            pickPhotosButton.isEnabled = false
//        }
    }

    private fun hasRequiredPermissions(): Boolean {
        val permissionResults = PERMISSIONS.map { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }

        return permissionResults.all { isGranted -> isGranted }
    }

    override fun onRequestPermissionsResult(
            code: Int,
            permissions: Array<String>,
            result: IntArray) {
        super.onRequestPermissionsResult(code, permissions, result)
        if (code == PERMISSIONS_REQUEST_CODE) {
            requestPermissionsIfNecessary()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null && resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            val apiService = RestApiService()
            val uri = data.data!!
            val inputStream = this.contentResolver.openInputStream(uri)

            if (inputStream != null) {
                apiService.upload(inputStream,"Test","73666","image/jpeg") {
                    if(it?.status == "1") {
                        print(it)
                        Toast.makeText(this, it?.message, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun list(){
        val apiService = RestApiService()
        apiService.list {
            print(it)
        }
    }

}