package dev.Chirii.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.Chirii.workoutlog.databinding.ActivityLoginBinding
import dev.Chirii.workoutlog.databinding.ActivitySignupBinding

class signupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_signup)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            var intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
    }
    fun validateSignup(){
        var firstname = binding.etFirstName.text.toString()
        var lastname = binding.etLastName.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmPassword = binding.etConfirmPassword.text.toString()
        var email = binding.etEmail.text.toString()

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
        if (confirmPassword.isBlank()){
            binding.tilConfirmPassword.error = getString(R.string.Confirm_password)
        }
        if (confirmPassword.length<8){
            binding.tilConfirmPassword.error = "Password is too short"
        }
        if (confirmPassword.length>16){
            binding.tilConfirmPassword.error = "Password is too long"
        }

    }

}