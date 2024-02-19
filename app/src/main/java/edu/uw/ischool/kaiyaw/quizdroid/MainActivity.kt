package edu.uw.ischool.kaiyaw.quizdroid

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvTopics: RecyclerView
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.barApplication))

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        pendingIntent = PendingIntent.getBroadcast(
            this, 0, Intent("edu.uw.ischool.kaiyaw.quizdroid.MReceiver").apply {
            }, PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            getSharedPreferences(getString(R.string.pref_file_key), MODE_PRIVATE)
                .getString(getString(R.string.saved_interval_key), "1")!!.toLong() * 60L * 1000L,
            pendingIntent
        )

        rvTopics = findViewById(R.id.rvTopics)
        rvTopics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTopics.adapter = QuizListAdapter(QuizApp.topics)
    }

    override fun onResume() {
        super.onResume()

        rvTopics = findViewById(R.id.rvTopics)
        rvTopics.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTopics.adapter = QuizListAdapter(QuizApp.topics)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actPreferences -> {
                val intent = Intent(this, PreferencesActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        alarmManager.cancel(pendingIntent)
    }
}