package com.example.passauth.ui.passwords

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import com.example.passauth.R
import com.example.passauth.common.PersistencyManager
import com.example.passauth.databinding.ShowPasswordsFragmentBinding
import com.example.passauth.ui.main.MainFragment

class ShowPasswordsFragment : Fragment() {

    companion object {
        fun newInstance() = ShowPasswordsFragment()
    }

    private lateinit var viewModel: ShowPasswordsViewModel

    private var _binding: ShowPasswordsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShowPasswordsFragmentBinding.inflate(inflater, container, false)

        val allPasswords = PersistencyManager.getPasswords(activity!!)
        for (password in allPasswords) {
            val passwordView: TextView? = TextView(context)
            passwordView!!.text = password.key + ": " + password.value
            passwordView.setTextColor(getColor(context!!, R.color.colorAccent))
            passwordView.gravity = Gravity.CENTER
            passwordView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24f)
            binding.passwordsLinearLayout.addView(passwordView)
        }

        binding.backButton.setOnClickListener {
            val mainFragment = MainFragment.newInstance()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
        }

        binding.clearButton.setOnClickListener {
            PersistencyManager.clearPersistence(activity!!)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShowPasswordsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}