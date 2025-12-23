package com.example.effectivemobile.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.UserActivity
import com.example.effectivemobile.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = "Нету аккаунта? Регистрация"
        val spannable = SpannableString(text)

        val start = text.indexOf("Регистрация")
        val end = start + "Регистрация".length

        // Green color
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.greenText)),
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Click action
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(
                    R.id.signUpFragment,
                    null,
                    androidx.navigation.NavOptions.Builder()
                        .setPopUpTo(R.id.signInFragment, true) // finish SignUpFragment
                        .build()
                )
            }
        }

        spannable.setSpan(
            clickableSpan,
            start,
            end,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textView.text = spannable
        binding.textView.movementMethod = LinkMovementMethod.getInstance()

        binding.buttonSignIn.setOnClickListener {

            // save login state
            val prefs = requireContext()
                .getSharedPreferences("auth_prefs", android.content.Context.MODE_PRIVATE)

            prefs.edit()
                .putBoolean("is_logged_in", true)
                .apply()

            val intent = Intent(requireContext(), UserActivity::class.java)
            startActivity(intent)

            requireActivity().finish()
        }


        binding.buttonVkontakte.setOnClickListener {
            val url = "https://vk.com/"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.buttonOdnoklassniki.setOnClickListener {
            val url = "https://ok.ru/"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}