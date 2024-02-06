package edu.uw.ischool.kaiyaw.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import java.io.Serializable

class AnswerActivity : AppCompatActivity() {
    private lateinit var txtAnswered: TextView
    private lateinit var txtCorrect: TextView
    private lateinit var txtRatio: TextView
    private lateinit var btnNextFinish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        txtAnswered = findViewById(R.id.txtAnswered)
        txtCorrect = findViewById(R.id.txtCorrect)
        txtRatio = findViewById(R.id.txtRatio)
        btnNextFinish = findViewById(R.id.btnNextFinish)

        val questions = intent.getSerializableExtra("Questions") as List<Question>
        val answered = intent.getStringExtra("Answered")
        val correct = intent.getStringExtra("Correct")
        val answeredQuestions = intent.getIntExtra("AnsweredQuestions", 0)
        val correctQuestions = intent.getIntExtra("CorrectQuestions", 0)
        val questionNum = intent.getIntExtra("QuestionNum", 0)

        txtAnswered.text = getString(R.string.txt_answered, answered)
        txtCorrect.text = getString(R.string.txt_correct, correct)
        txtRatio.text = getString(R.string.txt_ratio, correctQuestions, answeredQuestions)

        if (questionNum == questions.size - 1) {
            btnNextFinish.text = getString(R.string.btn_finish)
            btnNextFinish.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            btnNextFinish.text = getString(R.string.btn_next)
            btnNextFinish.setOnClickListener {
                val intent = Intent(this, QuestionActivity::class.java).apply {
                    putExtra("OrigIntent", intent.getParcelableExtra<Intent>("OrigIntent")!! as Parcelable)
                    putExtra("Questions", questions as Serializable)
                    putExtra("Answered", "")
                    putExtra("Correct", "")
                    putExtra("AnsweredQuestions", answeredQuestions)
                    putExtra("CorrectQuestions", correctQuestions)
                    putExtra("QuestionNum", questionNum + 1)
                }
                startActivity(intent)
            }
        }
    }
}