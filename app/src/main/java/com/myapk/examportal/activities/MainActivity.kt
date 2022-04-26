package com.myapk.examportal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.myapk.examportal.R
import com.myapk.examportal.adapters.QuizAdapter
import com.myapk.examportal.databinding.ActivityMainBinding
import com.myapk.examportal.models.Quiz

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private var quizList = mutableListOf<Quiz>()
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var adapter : QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        //setUpFireStore()
        setUpDrawerLayout()
        setUpFireStore()
        setUpRecyclerView(quizList)

    }

    private fun setUpFireStore(){
        firebaseFirestore = FirebaseFirestore.getInstance()
        val collectionReference: CollectionReference = firebaseFirestore.collection("Subjects")

        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this, "Error Fetching data!! Try again after some time.", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            //  Log.d("firebasedataMain",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()

        }
    }

    private fun setUpRecyclerView(quiz:List<Quiz>){
        adapter = QuizAdapter(this,quiz)
        binding?.quizRecyclerView?.adapter = adapter
        binding?.quizRecyclerView?.layoutManager = GridLayoutManager(this,2)
    }

    private fun setUpDrawerLayout(){
        setSupportActionBar(binding?.toolBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding?.drawerLayout,R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
        binding?.navigationView!!.setNavigationItemSelectedListener {
            binding?.drawerLayout!!.closeDrawers()
            val intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)

    }

}