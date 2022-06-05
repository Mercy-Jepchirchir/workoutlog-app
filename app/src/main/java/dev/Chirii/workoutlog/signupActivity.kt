package dev.Chirii.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class signupActivity : AppCompatActivity() {
    lateinit var tvLogin :TextView
    lateinit var imgFirstName: ImageView
    lateinit var tilFirstname: TextInputLayout
    lateinit var etFirstName: TextInputEditText
    lateinit var imgLastName: ImageView
    lateinit var tilLastname: TextInputLayout
    lateinit var etLastName: TextInputEditText
    lateinit var imgEmail: ImageView
    lateinit var tilEmail: TextInputLayout
    lateinit var etEmail: TextInputEditText
    lateinit var imgPassword: ImageView
    lateinit var etPassword: TextInputEditText
    lateinit var tilPassword: TextInputLayout
    lateinit var imgConfirmPassword: ImageView
    lateinit var etConfirmPassword: TextInputEditText
    lateinit var tilConfirmPassword: TextInputLayout
    lateinit var btnSignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        tvLogin = findViewById(R.id.tvLogin)
        imgFirstName = findViewById(R.id.imgFirstName)
        tilFirstname = findViewById(R.id.tilFirstname)
        etFirstName = findViewById(R.id.etFirstName)
        imgLastName = findViewById(R.id.imgLastName)
        tilLastname = findViewById(R.id.tilLastname)
        etLastName = findViewById(R.id.etLastName)
        imgEmail = findViewById(R.id.imgEmail)
        tilEmail = findViewById(R.id.tilEmail)
        etEmail = findViewById(R.id.etEmail)
        imgPassword = findViewById(R.id.imgPassword)
        etPassword = findViewById(R.id.etPassword)
        tilPassword = findViewById(R.id.tilPassword)
        imgConfirmPassword = findViewById(R.id.imgConfirmPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)
        btnSignup = findViewById(R.id.btnSignup)



        tvLogin.setOnClickListener {
            var intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
        btnSignup.setOnClickListener {
            validateSignup()
        }
    }
    fun validateSignup(){
        var firstname = etFirstName.text.toString()
        var lastname = etLastName.text.toString()
        var password = etPassword.text.toString()
        var confirmPassword = etConfirmPassword.text.toString()
        var email = etEmail.text.toString()

        if (firstname.isBlank()){
            tilFirstname.error = getString(R.string.first_name)
        }
        if (lastname.isBlank()){
            tilLastname.error = getString(R.string.Last_name)
        }
        if (password.isBlank()){
            tilPassword.error = getString(R.string.Password)
        }
        if (confirmPassword.isBlank()){
            tilConfirmPassword.error = getString(R.string.Confirm_password)
        }
        if (email.isBlank()){
            tilEmail.error = getString(R.string.Enter_Email)
        }
    }

}