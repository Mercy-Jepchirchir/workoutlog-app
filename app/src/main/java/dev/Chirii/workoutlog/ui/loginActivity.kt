package dev.Chirii.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.Chirii.workoutlog.R
import dev.Chirii.workoutlog.databinding.ActivityLoginBinding
import dev.Chirii.workoutlog.models.LoginRequest
import dev.Chirii.workoutlog.models.LoginResponse
import dev.Chirii.workoutlog.api.ApiClient
import dev.Chirii.workoutlog.api.ApiInterface
import dev.Chirii.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharePrefs:SharedPreferences
    val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharePrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, signupActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer{ loginResponse->
            SaveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErrorLiveData.observe(this, Observer{ error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
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
            binding.pbLogin.visibility = View.VISIBLE

            var loginRequest = LoginRequest(email,password)
            userViewModel.loginUser(loginRequest)
        }

    }
    fun SaveLoginDetails(loginResponse: LoginResponse){
        val editor = sharePrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()

    }

}