package com.example.sharewithme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.helperlibrary.showToast
import com.example.sharewithme.databinding.DialogMyBinding

class MyDialogFragment : DialogFragment() {

    private lateinit var binding: DialogMyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            isCancelable = false
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            it.window?.apply {
                setLayout(width, height)
            }
        }
    }


    private fun setClickListeners() = with(binding) {
        okButton.setOnClickListener {
            requireContext().showToast("Ok")
            dismiss()
        }
        cancelButton.setOnClickListener {
            requireContext().showToast("Cancel")
            dismiss()
        }
    }

}