package com.example.sharewithme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.helperlibrary.showToast
import com.example.sharewithme.databinding.DialogMyBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomsheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogMyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogMyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        (dialog as? BottomSheetDialog)?.apply {
            setCanceledOnTouchOutside(false)
            with(behavior) {
//                state = BottomSheetBehavior.STATE_EXPANDED
//                isDraggable = false
//                isHideable = false
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