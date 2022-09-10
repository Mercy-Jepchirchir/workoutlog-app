package dev.Chirii.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.Chirii.workoutlog.R
import dev.Chirii.workoutlog.databinding.ActivitySignupBinding
import dev.Chirii.workoutlog.models.RegisterRequest
import dev.Chirii.workoutlog.models.RegisterResponse
import dev.Chirii.workoutlog.api.ApiClient
import dev.Chirii.workoutlog.api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class signupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_signup)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            var intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
    }
    fun validateSignup(){
        var phoneNumber = binding.etPhoneNumber.text.toString()
        var firstname = binding.etFirstName.text.toString()
        var lastname = binding.etLastName.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmPassword = binding.etConfirmPassword.text.toString()
        var email = binding.etEmail.text.toString()

        var error = false

        if (firstname.isBlank()){
           binding.tilFirstname.error = getString(R.string.first_name)
        }
        if (lastname.isBlank()){
            binding.tilLastname.error = getString(R.string.Last_name)
        }
        if (password.isBlank()) {
            binding.tilPassword.error = getString(R.string.Password)
        }
        if (password.length<8){
            binding.tilPassword.error = "Password is too short"
        }
        if (password.length>16){
            binding.tilPassword.error = "Password is too long"
        }
        if (email.isBlank()){
            binding.tilEmail.error = getString(R.string.Enter_Email)
        }
        if (phoneNumber.isBlank()) {
            binding.tilPhoneNumber.error = "phone number is required"
        }
        if (confirmPassword.isBlank()){
            binding.tilConfirmPassword.error = getString(R.string.Confirm_password)
        }
        if (confirmPassword.length<8){
            binding.tilConfirmPassword.error = "Password is too short"
        }
        if (confirmPassword.length>16){
            binding.tilConfirmPassword.error = "Password is too long"
        }

        if(!error){
            val registerRequest = RegisterRequest(firstname,lastname,email,password,phoneNumber)
            makeRegistrationRequest(registerRequest)
        }

    }

    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiclient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiclient.registerUser(registerRequest)

        request.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if(response.isSuccessful){
                    var message = response.body()?.message
                    Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext,loginActivity::class.java))

                    // intent to login
                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })



    }

}