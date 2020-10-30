package ru.sumin.usersphoto

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import ru.sumin.usersphoto.pojo.User
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class MainActivity : AppCompatActivity() {

    private val mapper = Mapper()
    private val adapter = UsersAdapter()

    private lateinit var recyclerViewUsers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers)
        recyclerViewUsers.adapter = adapter
        adapter.onUserClickListener = {
            val intent = PhotosActivity.newIntent(this, it.id)
            startActivity(intent)
        }
        DownloadUsersTask().execute()
    }

    inner class DownloadUsersTask : AsyncTask<Void, Void, List<User>>() {
        override fun doInBackground(vararg p0: Void?): List<User> {
            return try {
                val urlConnection = URL(LOAD_USERS_URL).openConnection()
                val inputStreamReader = BufferedReader(
                    InputStreamReader(urlConnection.getInputStream())
                )
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
            adapter.users = result
        }
    }

    companion object {
        private const val LOAD_USERS_URL = "https://jsonplaceholder.typicode.com/users"
    }
}
