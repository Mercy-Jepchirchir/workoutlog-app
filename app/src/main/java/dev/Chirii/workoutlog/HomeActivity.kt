package dev.Chirii.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.Chirii.workoutlog.databinding.ActivityHomeBinding
import dev.Chirii.workoutlog.databinding.ActivityLoginBinding

class HomeActivity : AppCompatActivity() {
  lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()

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