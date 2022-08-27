package dev.Chirii.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.Chirii.workoutlog.R
import dev.Chirii.workoutlog.databinding.ActivityLoginBinding

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnLogin.setOnClickListener {
            validateLogin()
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, signupActivity::class.java)
            startActivity(intent)
        }


        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
    }

    fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error = false

        if (email.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_required)
            error = true
        }
        if (email.isBlank()) {
            binding.tilPassword.error = getString(R.string.password_required)
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "password is required"
            error = true
        }
        if (!error) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

    }

}