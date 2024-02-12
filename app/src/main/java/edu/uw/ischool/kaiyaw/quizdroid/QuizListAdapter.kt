package edu.uw.ischool.kaiyaw.quizdroid

import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuizListAdapter(val topics: List<Topic>) :
    RecyclerView.Adapter<QuizListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.topic_list_item, parent, false
                )
        )
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.iconField.setImageResource(topics[position].icon_id)
        holder.textField.text = topics[position].name
        holder.view.setOnClickListener {
            val intent = Intent(holder.view.context, TopicOverviewActivity::class.java).apply {
                putExtra("Topic", topics[position])
            }
            holder.view.context.startActivity(intent)
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var iconField = view.findViewById<ImageView>(R.id.icTopicListItem)
        var textField = view.findViewById<TextView>(R.id.txtTopicItemName)
    }
}