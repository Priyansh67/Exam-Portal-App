package com.myapk.examportal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.myapk.examportal.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private var binding: ActivitySignUpBinding?= null

    lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding?.btnSignUp?.setOnClickListener {
            signUp()
        }

        binding?.btnLogin?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUp(){
        val email = binding?.etEmailAddress?.text.toString()
        val password = binding?.etPassword?.text.toString()
        val confirmPassword = binding?.etConfirmPassword?.text.toString()

        if(password.length < 6){
            Toast.makeText(this, "Password should be atleast of 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this, "Email and password can not be empty!!", Toast.LENGTH_SHORT).show()
            return
        }

        if(password != confirmPassword){
            Toast.makeText(this, "Passwords are different", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
            if(it.isSuccessful){
                Toast.makeText(this, "SignUp Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Cannot create User at this time", Toast.LENGTH_SHORT).show()
            }
        }

    }
}