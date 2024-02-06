package edu.uw.ischool.kaiyaw.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.Serializable

class TopicOverviewActivity : AppCompatActivity() {
    private lateinit var txtTopicName: TextView
    private lateinit var txtTopicDesc: TextView
    private lateinit var btnTopicBegin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        txtTopicName = findViewById(R.id.txtTopicName)
        txtTopicDesc = findViewById(R.id.txtTopicDesc)
        btnTopicBegin = findViewById(R.id.btnTopicBegin)

        val topic = intent.getSerializableExtra("Topic") as Topic
        txtTopicName.text = topic.name
        txtTopicDesc.text = topic.desc

        btnTopicBegin.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java).apply {
                putExtra("Questions", topic.questions as Serializable)
                putExtra("Answered", "")
                putExtra("Correct", "")
                putExtra("AnsweredQuestions", 0)
                putExtra("CorrectQuestions", 0)
                putExtra("QuestionNum", 0)
            }
            startActivity(intent)
        }
    }
}