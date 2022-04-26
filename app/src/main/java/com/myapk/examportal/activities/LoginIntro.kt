package com.myapk.examportal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.myapk.examportal.databinding.ActivityLoginIntroBinding

class LoginIntro : AppCompatActivity() {
    lateinit var binding : ActivityLoginIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            redirect("Main")
        }

        binding.getStarted.setOnClickListener {
            redirect("Login")
        }
    }

    private fun redirect(activity: String){
        val intent = when(activity){
            "Login" -> Intent(this,LoginActivity::class.java)
            "Main" -> Intent(this,MainActivity::class.java)
            else-> throw Exception("No Path Exists")
        }
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}