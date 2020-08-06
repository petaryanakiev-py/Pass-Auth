package com.example.passauth.ui.main

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.passauth.R
import com.example.passauth.common.BiometricsUtil
import com.example.passauth.databinding.MainFragmentBinding
import com.example.passauth.ui.passwords.NewPasswordFragment
import java.util.concurrent.Executor


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private fun showBiomertricDialog() {
        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)

                    Toast.makeText(context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    showPasswordsButtons()
                    binding.saveNewPasswordButton.setOnClickListener {
                        val newPasswordFragment = NewPasswordFragment.newInstance()
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.container, newPasswordFragment)
                            .commitNow()
                    }
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "Authentication failed. Try again :(",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for this app")
            .setSubtitle("Place your fingertip on the sensor")
            .setNegativeButtonText("Cancel")
            .build()
        biometricPrompt.authenticate(promptInfo)

    }

    private fun showPasswordsButtons() {
        binding.saveNewPasswordButton.visibility = View.VISIBLE
        binding.viewPasswordsButton.visibility = View.VISIBLE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        val view = binding.root

        if (BiometricsUtil.hasBiometricEnrolled(context!!) && BiometricsUtil.isHardwareAvailable(context!!)) {
            showBiomertricDialog()
        } else {
            Toast.makeText(context,
                "Sorry, but your hardware does not support biometric data or you haven't set fingerprint in the settings of your device!",
                Toast.LENGTH_LONG).show()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

    }

}