package edu.uw.ischool.kaiyaw.quizdroid

import java.io.Serializable

data class Question(val title: String, val choices: List<String>, val answerIdx: Int) : Serializable