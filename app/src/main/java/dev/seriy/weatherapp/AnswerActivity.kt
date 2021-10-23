package dev.seriy.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.seriy.weatherapp.api.RetrofirUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnswerActivity : AppCompatActivity() {
    private val weather: TextView by lazy { findViewById(R.id.weather_value) }
    private val temperature: TextView by lazy { findViewById(R.id.temp_value) }
    private val humidity: TextView by lazy { findViewById(R.id.humidity_value) }
    private val windSpeed: TextView by lazy { findViewById(R.id.wind_speed) }
    private val updateData: SwipeRefreshLayout by lazy { findViewById(R.id.refresh_data) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        CoroutineScope(Dispatchers.Main).launch {
            updateData.isRefreshing = true
            setViews()
        }
        updateData.setOnRefreshListener {
            CoroutineScope(Dispatchers.Main).launch {
                setViews()
            }
        }
    }

    private suspend fun setViews() {
        val response =
            RetrofirUtil.weather.getWeather(
                intent.getStringExtra("city").orEmpty(),
                "1d35fcd2df9ac6cec4b3bed472e32dcc",
                "ru",
                "metric"
            )
        val body = response.body()
        if (response.isSuccessful) {
            if (body != null) {
                weather.text = body.weather[0].main
                temperature.text = body.main.temp.toString()
                humidity.text = body.main.humidity.toString()
                windSpeed.text = body.wind.speed.toString()
//                    this.putExtra("temp",  body.main.temp )
//                    this.putExtra("hum",  body.main.humidity )
//                    this.putExtra("windSpeed",  body.wind.speed)
//                    this.putExtra("cloudPercent", body.clouds.all )
//                    this.putExtra("weather",  body.weather[0].main)
            }
        }else Toast.makeText(this,"City error",Toast.LENGTH_SHORT).show()
        updateData.isRefreshing = false
    }
}