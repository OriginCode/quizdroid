package edu.uw.ischool.kaiyaw.quizdroid

import java.io.Serializable

data class Topic(val name: String, val desc: String, val questions: List<Question>) : Serializable