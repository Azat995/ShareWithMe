package com.example.sharewithme

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.helperlibrary.showToast
import com.example.sharewithme.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.apply {
            openButton.setOnClickListener {
                openMyBottomsheetDialogFragment()
            }
        }
    }

    private fun openMyBottomsheetDialogFragment() {
        val dialog = MyBottomsheetDialogFragment()
        dialog.show(supportFragmentManager, dialog::class.java.simpleName)
    }

    private fun openMyDialogFragment() {
        val dialog = MyDialogFragment()
        dialog.show(supportFragmentManager, dialog::class.java.simpleName)
    }

    private fun openAlertDialog() {
        val alertDialog = AlertDialog.Builder(this).apply {
            setTitle("My Title")
            setMessage("My Message Message Message Message")
            setPositiveButton("Ok") { dialog, which ->
                showToast("Positive")
            }
            setNegativeButton("No") { dialog, which ->
                showToast("Negative")
            }
            setNeutralButton("I understand") { dialog, which ->
                showToast("Neutral")
            }
        }
        alertDialog.show()
    }
}