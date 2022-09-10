package dev.Chirii.workoutlog.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.Chirii.workoutlog.R
import dev.Chirii.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
  lateinit var binding:ActivityHomeBinding
  lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()

        binding.tvLogout.setOnClickListener {
            sharedPref = getSharedPreferences("WORKOUTLOG_PREFS", Context.MODE_PRIVATE)
            val editor=sharedPref.edit()
            editor.putString("ACCESS_TOKEN", "")
            editor.putString("USER_ID", "")
            editor.putString("PROFILE_ID", "")
            editor.apply()
            startActivity(Intent(this,loginActivity::class.java))
        }

    }



    fun setupBottomNav() {
        binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, planFragment()).commit()
                    true

                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, trackFragment()).commit()
                    true

                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment()).commit()
                    true    
                }
                else -> false
            }

        }
    }
}