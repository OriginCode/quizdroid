package edu.uw.ischool.kaiyaw.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : android.app.Application() {
    override fun onCreate() {
        super.onCreate()

        Log.i("QuizApp", "Loaded and running!")
    }

    companion object {
        val topics by lazy {
            PlainTextTopicRepository().getTopics()
        }
    }
}