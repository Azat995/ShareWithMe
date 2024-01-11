package com.example.sharewithme

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sharewithme.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val singlePhotoPickerLauncher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
//            sendImageMessage(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.apply {
            shareButton.setOnClickListener {
                launch()
            }
//            settingsButton.setOnClickListener {
//                val myAppSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
//                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT)
//                myAppSettings.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(myAppSettings)
//            }
        }
    }

    fun launch() {
        if (ActivityResultContracts.PickVisualMedia.isPhotoPickerAvailable(this)) {
            singlePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            Toast.makeText(this, "Not available", Toast.LENGTH_SHORT).show()
        }
    }


    val imagePickerActivityResultLauncher: ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.GetContent()) { selectedImageUri ->
        if (selectedImageUri != null) {
//                    binding.imageView.load(selectedImageUri)
//            binding.imageView.setVideoURI(selectedImageUri)
        }
    }
//    imagePickerActivityResultLauncher.launch("image/*")

    private val permissionReadExternalResult = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            // Permission is granted, so open the image picker.
            singlePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            // Permission is not granted, so show a toast message to the user.
        }
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, "Our Subject")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("khazaryaneva@mail.ru"))
            putExtra(Intent.EXTRA_TEXT, getSystemDetail())
        }

        startActivity(Intent.createChooser(intent, "Share Via"))
    }

    @SuppressLint("HardwareIds")
    private fun getSystemDetail(): String {
        val pInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
        val version = pInfo.versionName
        return """ 
            Brand: ${Build.BRAND}
            App version: $version 
            
              "DeviceID: ${
            Settings.Secure.getString(
                contentResolver,
                Settings.Secure.ANDROID_ID
            )
        } 
                Model: ${Build.MODEL} 
                ID: ${Build.ID}
                SDK: ${Build.VERSION.SDK_INT}
                Manufacture: ${Build.MANUFACTURER} 
                Brand: ${Build.BRAND} 
                User: ${Build.USER} 
                Type: ${Build.TYPE} 
                Base: ${Build.VERSION_CODES.BASE} 
                Incremental: ${Build.VERSION.INCREMENTAL} 
                Board: ${Build.BOARD} 
                Host: ${Build.HOST} 
                FingerPrint: ${Build.FINGERPRINT} 
                Version Code: ${Build.VERSION.RELEASE}
        """.trimIndent()

//                "App version: $version "
    }

}