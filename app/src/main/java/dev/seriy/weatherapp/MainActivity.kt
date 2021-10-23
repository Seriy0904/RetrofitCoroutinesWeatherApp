package dev.seriy.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.*
import dev.seriy.weatherapp.api.RetrofirUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val cityName: EditText by lazy { findViewById(R.id.edit_city) }
    private val update: Button by lazy { findViewById(R.id.get_weather) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        update.setOnClickListener {
            val answerActivity = Intent(this, AnswerActivity::class.java)
            answerActivity.putExtra("city", cityName.text.toString())
            startActivity(answerActivity)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        cityName.setText(outState.getString("CityName").orEmpty())
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.putString("CityName",cityName.text.toString())
    }
}