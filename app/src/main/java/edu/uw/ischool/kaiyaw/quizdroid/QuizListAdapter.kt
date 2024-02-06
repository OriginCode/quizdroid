package edu.uw.ischool.kaiyaw.quizdroid

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuizListAdapter(val topics: List<Topic>) :
    RecyclerView.Adapter<QuizListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    android.R.layout.simple_list_item_1, parent, false
                )
        )
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textField.text = topics[position].name
        holder.textField.setOnClickListener {
            val intent = Intent(holder.view.context, TopicOverviewActivity::class.java).apply {
                putExtra("Topic", topics[position])
            }
            holder.view.context.startActivity(intent)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textField = view.findViewById<TextView>(android.R.id.text1)
        val view = view
    }
}