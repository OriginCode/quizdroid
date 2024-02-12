package edu.uw.ischool.kaiyaw.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var rvTopics: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTopics = findViewById(R.id.rvTopics)
        rvTopics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTopics.adapter = QuizListAdapter(QuizApp.topics)
    }
}