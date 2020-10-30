package ru.sumin.usersphoto

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.loader.content.AsyncTaskLoader
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val mapper = Mapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DownloadUsersTask().execute()
    }

    inner class DownloadUsersTask: AsyncTask<Void, Void, JSONArray>() {
        override fun doInBackground(vararg p0: Void?): JSONArray {
            val urlConnection = URL(LOAD_USERS_URL).openConnection()
            val inputStreamReader = BufferedReader(InputStreamReader(urlConnection.getInputStream()))
            val builder = StringBuilder()
            var line = inputStreamReader.readLine()
            while (line != null) {
                builder.append(line)
                line = inputStreamReader.readLine()
            }
            return JSONArray(builder.toString())
        }

        override fun onPostExecute(result: JSONArray?) {
            super.onPostExecute(result)
            Log.d("MY_LOG_EVENT", result.toString())
        }
    }

    companion object {
        private const val LOAD_USERS_URL = "https://jsonplaceholder.typicode.com/users"
    }
}
