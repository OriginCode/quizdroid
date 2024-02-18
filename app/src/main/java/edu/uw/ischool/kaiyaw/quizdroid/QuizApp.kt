package edu.uw.ischool.kaiyaw.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log

class QuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        QuizApp.context = applicationContext
        Log.i("QuizApp", "Loaded and running!")
    }

    companion object {
        lateinit var context: Context
        val topics by lazy {
            // PlainTextTopicRepository().getTopics()
            LocalStorageTopicRepository().getTopics()
        }
    }
}