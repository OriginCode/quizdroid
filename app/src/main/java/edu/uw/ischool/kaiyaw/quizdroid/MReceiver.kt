package edu.uw.ischool.kaiyaw.quizdroid

import android.annotation.SuppressLint
import android.app.Application
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.util.Log
import java.io.File

class MReceiver: BroadcastReceiver() {
    companion object {
        val intentFilter = IntentFilter("edu.uw.ischool.kaiyaw.quizdroid.MReceiver")
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        retrieveTopics(context!!)
    }

    @SuppressLint("Range")
    fun retrieveTopics(context: Context) {
        val downloadManager = context.getSystemService(Application.DOWNLOAD_SERVICE) as DownloadManager
        val pref = context.getSharedPreferences(context.getString(R.string.pref_file_key), Application.MODE_PRIVATE)
        val url = pref.getString(context.getString(R.string.saved_url_key), context.getString(R.string.default_url))
        val file = File(context.externalCacheDir, "questions.json")

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
                        file.copyTo(File(context.filesDir, "questions.json"), true)
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

}