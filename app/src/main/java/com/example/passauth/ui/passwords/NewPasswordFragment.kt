package com.example.passauth.ui.passwords

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getSystemService
import com.example.passauth.R
import com.example.passauth.databinding.FragmentNewPasswordBinding
import com.example.passauth.databinding.MainFragmentBinding

class NewPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = NewPasswordFragment()
    }

    private lateinit var viewModel: NewPasswordViewModel

    private var _binding: FragmentNewPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewPasswordBinding.inflate(inflater, container, false)

        binding.saveButton.setOnClickListener {
            val appName = binding.inputApplication.text.toString()
            if (appName == "") {
                Toast.makeText(context, "Application name is required", Toast.LENGTH_SHORT).show()
                binding.inputApplication.setHintTextColor(Color.RED)
                binding.inputApplication.setOnFocusChangeListener { v, hasFocus ->
                    if (hasFocus) {
                        binding.inputApplication.setHintTextColor(getColor(context!!, R.color.colorAccent))
                    }
                }
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}