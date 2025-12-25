package com.example.effectivemobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.effectivemobile.databinding.ActivityMainBinding
import com.example.effectivemobile.databinding.ActivityUserBinding
import com.example.effectivemobile.fragments.AccountFragment
import com.example.effectivemobile.fragments.BookmarkFragment
import com.example.effectivemobile.fragments.HomeFragment

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("auth_prefs", MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("is_logged_in", false)

        if (!isLoggedIn) {
            finish()
            return
        }
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.saved -> replaceFragment(BookmarkFragment())
                R.id.account -> replaceFragment(AccountFragment())

                else -> {}
            }
            true
        }
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, HomeFragment())
            .commit()

        // Listen for back stack changes
        supportFragmentManager.addOnBackStackChangedListener {
            // Show bottom nav only if the top fragment is NOT CourseFragment
            val topFragment = supportFragmentManager.fragments.lastOrNull()
            if (topFragment is com.example.effectivemobile.fragments.CourseFragment) {
                hideBottomNavigation()
            } else {
                showBottomNavigation()
            }
        }
    }

    fun hideBottomNavigation() {
        binding.bottomNavigationView.visibility = android.view.View.GONE
    }

    fun showBottomNavigation() {
        binding.bottomNavigationView.visibility = android.view.View.VISIBLE
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}