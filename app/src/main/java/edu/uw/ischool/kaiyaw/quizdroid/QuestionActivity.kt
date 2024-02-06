package edu.uw.ischool.kaiyaw.quizdroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import java.io.Serializable

class QuestionActivity : AppCompatActivity() {
    private lateinit var txtQuestion: TextView
    private lateinit var rgrpChoices: RadioGroup
    private lateinit var rbtnChoice1: RadioButton
    private lateinit var rbtnChoice2: RadioButton
    private lateinit var rbtnChoice3: RadioButton
    private lateinit var rbtnChoice4: RadioButton
    private lateinit var btnQuestionSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        txtQuestion = findViewById(R.id.txtQuestion)
        rgrpChoices = findViewById(R.id.rgrpChoices)
        rbtnChoice1 = findViewById(R.id.rbtnChoice1)
        rbtnChoice2 = findViewById(R.id.rbtnChoice2)
        rbtnChoice3 = findViewById(R.id.rbtnChoice3)
        rbtnChoice4 = findViewById(R.id.rbtnChoice4)
        btnQuestionSubmit = findViewById(R.id.btnQuestionSubmit)

        val questions = intent.getSerializableExtra("Questions") as List<Question>
        val answeredQuestions = intent.getIntExtra("AnsweredQuestions", 0)
        val correctQuestions = intent.getIntExtra("CorrectQuestions", 0)
        val questionNum = intent.getIntExtra("QuestionNum", 0)
        val question = questions[questionNum]

        txtQuestion.text = question.title
        Log.i("QuestionActivity", "QuestionNum: ${questionNum}, Questions: ${questions}")

        val rbtnChoices = listOf(rbtnChoice1, rbtnChoice2, rbtnChoice3, rbtnChoice4)
        rbtnChoices.withIndex().forEach { (i, it) ->
            it.text =
                question.choices[i]
        }

        rgrpChoices.setOnCheckedChangeListener { _, checkedId ->
            btnQuestionSubmit.visibility = if (checkedId == -1) View.INVISIBLE else View.VISIBLE
        }

        btnQuestionSubmit.setOnClickListener {
            if (rgrpChoices.checkedRadioButtonId != -1) {
                val selectedChoice = findViewById<RadioButton>(rgrpChoices.checkedRadioButtonId)
                val intent = Intent(this, AnswerActivity::class.java).apply {
                    putExtra("Questions", questions as Serializable)
                    putExtra("Answered", selectedChoice.text)
                    putExtra("Correct", question.choices[question.answerIdx])
                    putExtra(
                        "AnsweredQuestions", answeredQuestions + 1
                    )
                    putExtra(
                        "CorrectQuestions",
                        if (selectedChoice.text == question.choices[question.answerIdx]) correctQuestions + 1 else correctQuestions
                    )
                    putExtra("QuestionNum", questionNum)
                }
                startActivity(intent)
            }
        }
    }
}