package edu.uw.ischool.kaiyaw.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

val topics = listOf(
    Topic(
        name = "Math",
        desc = "Some number stuff",
        questions = listOf(
            Question(
                title = "1 + 1 = ?",
                choices = listOf("2", "3", "4", "5"),
                answerIdx = 0,
            ),
            Question(
                title = "2 * 4 = ?",
                choices = listOf("0", "124", "23", "8"),
                answerIdx = 3,
            ),
            Question(
                title = "124 / 124 = ?",
                choices = listOf("3", "1", "2", "9"),
                answerIdx = 1,
            ),
        )
    ),
    Topic(
        name = "Physics",
        desc = "Some physical stuff",
        questions = listOf(
            Question(
                title = "What is the value of the gravitational acceleration on Earth?",
                choices = listOf("5.6 m/s^2", "9.8 m/s^2", "6.3 m/s^2", "10.3 m/s^2"),
                answerIdx = 1,
            ),
            Question(
                title = "Which one is Newton's second law of motion?",
                choices = listOf("F = mg", "F = ma^2", "F = ma", "F = ma/g"),
                answerIdx = 2,
            ),
        )
    ),
    Topic(
        name = "Marvel Super Heroes",
        desc = "Some super hero stuff",
        questions = listOf(
            Question(
                title = "When did the first episode of the Marvel Super Heroes release?",
                choices = listOf("October 16, 1967", "September 1, 1966", "October 1, 1967", "September 12, 1966"),
                answerIdx = 1,
            ),
            Question(
                title = "What's the full name of the Scarlet Spider?",
                choices = listOf("Benjamin \"Ben\" Reilly", "Peter Benjamin Parker", "Steven Rogers", "Anthony Edward Stark"),
                answerIdx = 0,
            ),
        )
    ),
)

class MainActivity : AppCompatActivity() {
    private lateinit var rvItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvItems = findViewById(R.id.rvTopics)
        rvItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvItems.adapter = QuizListAdapter(topics)
    }
}