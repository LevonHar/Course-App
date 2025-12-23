package com.example.effectivemobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = findNavController()

        val sharedPref = requireActivity().getSharedPreferences("app_pref", 0)
        val onboardingCompleted = sharedPref.getBoolean("onboarding_completed", false)

        if (onboardingCompleted) {
            // If already completed, navigate directly to SignUpFragment
            controller.navigate(R.id.signUpFragment)
            return
        }

        binding.buttonContinue.setOnClickListener {
            sharedPref.edit().putBoolean("onboarding_completed", true).apply()
            controller.navigate(R.id.signUpFragment)
        }
    }
}