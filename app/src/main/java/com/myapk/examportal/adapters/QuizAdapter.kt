package com.myapk.examportal.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.myapk.examportal.activities.QuestionsActivity
import com.myapk.examportal.databinding.QuizItemBinding
import com.myapk.examportal.models.Quiz
import com.myapk.examportal.utils.ColorPicker
import com.myapk.examportal.utils.IconPicker


class QuizAdapter(val context: Context, val quizzes: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {


        return QuizViewHolder(QuizItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener {
            Toast.makeText(context, quizzes[position].title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, QuestionsActivity::class.java)
            intent.putExtra("Subject", quizzes[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizViewHolder(itemView: QuizItemBinding) : RecyclerView.ViewHolder(itemView.root) {
        var textViewTitle: TextView = itemView.quizTitle
        var iconView: ImageView = itemView.quizIcon
        var cardContainer: CardView = itemView.cardContainer
    }
}

