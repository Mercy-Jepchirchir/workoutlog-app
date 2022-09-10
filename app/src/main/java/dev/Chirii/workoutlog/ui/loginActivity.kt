package dev.Chirii.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.Chirii.workoutlog.R
import dev.Chirii.workoutlog.databinding.ActivityLoginBinding
import dev.Chirii.workoutlog.models.LoginRequest
import dev.Chirii.workoutlog.models.LoginResponse
import dev.Chirii.workoutlog.api.ApiClient
import dev.Chirii.workoutlog.api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharePrefs:SharedPreferences


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
            makeLoginRequest(loginRequest)
        }

    }
    fun makeLoginRequest(loginRequest: LoginRequest) {
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.login(loginRequest)

        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.pbLogin.visibility = View.GONE
               if (response.isSuccessful){
                   val loginResponse = response.body()
                   SaveLoginDetails(loginResponse!!)
                   Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                   startActivity(Intent(baseContext,HomeActivity::class.java))
                   finish()
               }
                else{
                    var error = response.errorBody()?.string()
                   Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility = View.GONE
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun SaveLoginDetails(loginResponse: LoginResponse){
        val editor = sharePrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()

    }

}