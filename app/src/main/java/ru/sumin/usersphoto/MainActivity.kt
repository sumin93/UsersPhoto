package ru.sumin.usersphoto

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.loader.content.AsyncTaskLoader
import org.json.JSONArray
import org.json.JSONObject
import ru.sumin.usersphoto.pojo.User
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

    inner class DownloadUsersTask: AsyncTask<Void, Void, List<User>>() {
        override fun doInBackground(vararg p0: Void?): List<User> {
            return try {
                val urlConnection = URL(LOAD_USERS_URL).openConnection()
                val inputStreamReader = BufferedReader(InputStreamReader(urlConnection.getInputStream()))
                val builder = StringBuilder()
                var line = inputStreamReader.readLine()
                while (line != null) {
                    builder.append(line)
                    line = inputStreamReader.readLine()
                }
                mapper.mapJsonToUserList(JSONArray(builder.toString()))
            } catch (e: Exception) {
                emptyList()
            }
        }

        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            if (result == null) return
            for (user in result) {
                println(user)
            }
        }
    }

    companion object {
        private const val LOAD_USERS_URL = "https://jsonplaceholder.typicode.com/users"
    }
}
