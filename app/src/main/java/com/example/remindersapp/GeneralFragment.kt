package com.example.remindersapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.remindersapp.PasswordFragment.Companion.PREF_BIKE_LOCK
import com.example.remindersapp.PasswordFragment.Companion.PREF_TABLET_PIN
import com.example.remindersapp.PasswordFragment.Companion.PREF_WIFI
import com.example.remindersapp.databinding.DialogEditReminderBinding
import com.example.remindersapp.databinding.FragmentGeneralBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GeneralFragment: Fragment() {

    private lateinit var binding: FragmentGeneralBinding
    private val preferences by lazy { requireActivity().getSharedPreferences("general", Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeneralBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValues()
        binding.cardViewBin.setOnClickListener { showEditDialog(PREF_BIN_DAY) }
        binding.cardViewInsurance.setOnClickListener { showEditDialog(PREF_INSURANCE) }
        binding.cardViewAnniversary.setOnClickListener { showEditDialog(PREF_ANNIVERSARY) }
    }

    private fun showEditDialog(preferenceKey: String) {
        val dialogBinding = DialogEditReminderBinding.inflate(requireActivity().layoutInflater)
        dialogBinding.editTextValue.setText(preferences.getString(preferenceKey, null))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Update value")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                preferences.edit { putString(preferenceKey, dialogBinding.editTextValue.text?.toString()) }
                displayValues()
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .show()
    }

    private fun displayValues() {
        binding.textViewBinDayValue.text = preferences.getString(PREF_BIN_DAY, null)
        binding.textViewInsuranceValue.text = preferences.getString(PREF_INSURANCE, null)
        binding.textViewAnniversaryValue.text = preferences.getString(PREF_ANNIVERSARY, null)
    }



    companion object {

        const val PREF_BIN_DAY = "pref_bin_day"
        const val PREF_INSURANCE = "pref_insurance"
        const val PREF_ANNIVERSARY = "pref_anniversary"

    }

}