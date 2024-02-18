package edu.uw.ischool.kaiyaw.quizdroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PreferencesActivity : AppCompatActivity() {
    lateinit var edtUrl: EditText
    lateinit var edtInterval: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        edtUrl = findViewById(R.id.edtUrl)
        edtInterval = findViewById(R.id.edtInterval)

        val pref = getSharedPreferences(getString(R.string.pref_file_key), MODE_PRIVATE)
        edtUrl.setText(pref.getString(getString(R.string.saved_url_key), getString(R.string.default_url)))
        edtInterval.setText(pref.getString(getString(R.string.saved_interval_key), getString(R.string.default_interval)))

        edtUrl.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                with (pref.edit()) {
                    putString(getString(R.string.saved_url_key), edtUrl.text.toString())
                    apply()
                }
            }
        })

        edtInterval.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                with (pref.edit()) {
                    putString(getString(R.string.saved_interval_key), edtInterval.text.toString())
                    apply()
                }
            }
        })
    }
}