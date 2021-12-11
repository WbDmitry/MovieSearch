package com.wbdmitry.moviesearch.ui.main.sittings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wbdmitry.moviesearch.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: SettingsFragmentBinding

    companion object {
        fun newInstance() = SettingsFragment()
    }

//    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}