package com.myapk.examportal.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.myapk.examportal.databinding.ActivityResultBinding
import com.myapk.examportal.models.Quiz
import com.myapk.examportal.models.Result
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    private var binding : ActivityResultBinding?=null
    private var score = 0
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        firebaseAuth = FirebaseAuth.getInstance()

        setUpResult()
        addResultToDatabase()
    }

    private fun addResultToDatabase() {
        val email = firebaseAuth.currentUser!!.email.toString()
        val score = score
        val dateTime = getCurrentDate()
        val subject = quiz.title
        val result = Result(email, score, subject, dateTime)

        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("results")
        collection.document().set(result)
    }

    fun getCurrentDate():String{
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:")

        return sdf.format(Date())
    }

    private fun setUpResult(){
        val quizData = intent.getStringExtra("Quiz")
        quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
            builder.append("<font color='#009688'>Your Answer: ${question.userAnswer}</font><br/><br/>")

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding?.txtAnswer!!.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            binding?.txtAnswer!!.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {
        for (entry in quiz.questions.entries) {
            val question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        binding?.txtScore?.text = "Your Score : $score"
    }


}