package edu.uw.ischool.kaiyaw.quizdroid

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Application
import android.app.DownloadManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import java.io.File

class QuizApp : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        registerReceiver(MReceiver(), MReceiver.intentFilter, RECEIVER_EXPORTED)
        retrieveTopics()
        Log.i("QuizApp", "Loaded and running!")
    }

    @SuppressLint("Range")
    fun retrieveTopics() {
        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val pref = getSharedPreferences(getString(R.string.pref_file_key), MODE_PRIVATE)
        val url = pref.getString(getString(R.string.saved_url_key), getString(R.string.default_url))
        val file = File(externalCacheDir, "questions.json")

        val request = DownloadManager.Request(Uri.parse(url))
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
            .setDestinationUri(Uri.fromFile(file))
        val downloadId = downloadManager.enqueue(request)
        var finished = false
        while (!finished) {
            val cursor = downloadManager.query(DownloadManager.Query().setFilterById(downloadId))
            if (cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                when (status) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        finished = true
                        file.copyTo(File(filesDir, "questions.json"), true)
                    }

                    DownloadManager.STATUS_FAILED -> {
                        finished = true
                        Log.e("QuizApp", "Download failed!")
                    }
                }
            }
            cursor.close()
        }
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