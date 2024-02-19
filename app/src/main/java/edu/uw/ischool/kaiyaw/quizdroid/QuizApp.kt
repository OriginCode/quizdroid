package edu.uw.ischool.kaiyaw.quizdroid

import android.app.Application
import android.content.Context
import android.util.Log

class QuizApp : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp", "Loaded and running!")
    }

    companion object {
        private var instance: QuizApp? = null
        val topics by lazy {
            // PlainTextTopicRepository().getTopics()
            LocalStorageTopicRepository().getTopics()
        }
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}