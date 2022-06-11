package dev.Chirii.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class loginActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword : TextInputLayout
    lateinit var  etEmail: TextInputEditText
    lateinit var etPassword:  TextInputEditText
    lateinit var tvSignup: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        tvSignup = findViewById(R.id.tvSignup)

        btnLogin.setOnClickListener {
            validateLogin()
            var intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        tvSignup.setOnClickListener {
            val intent =Intent(this, signupActivity::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener{
            validateLogin()
        }
    }
    fun validateLogin(){
        var email = etEmail.text.toString()
        var password = etPassword.text.toString()
        var error = false

        if (email.isBlank()){
            tilEmail.error = getString(R.string.email_required)
            error = true
        }
        if (email.isBlank()){
            tilPassword.error = getString(R.string.password_required)
        }
        if(password.isBlank()){
            tilPassword.error = "password is required"
            error= true
        }
        if (!error){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

    }

}