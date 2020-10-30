package ru.sumin.usersphoto

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import ru.sumin.usersphoto.pojo.Album
import ru.sumin.usersphoto.pojo.Photo
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class PhotosActivity : AppCompatActivity() {

    private val mapper = Mapper()
    private val adapter = PhotosAdapter()

    private lateinit var recyclerViewPhotos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        recyclerViewPhotos = findViewById(R.id.recyclerViewPhotos)
        recyclerViewPhotos.adapter = adapter
        val id = intent.getIntExtra(EXTRA_USER_ID, -1)
        DownloadPhotosTask(id).execute()
    }

    inner class DownloadPhotosTask(private val userId: Int) : AsyncTask<Void, Void, List<Photo>>() {
        override fun doInBackground(vararg p0: Void?): List<Photo> {
            return try {
                val albumList = loadAlbums(userId)
                val albumIds = albumList.map { it.id }
                return loadPhotos(albumIds)
            } catch (e: Exception) {
                emptyList()
            }
        }

        override fun onPostExecute(result: List<Photo>?) {
            super.onPostExecute(result)
            if (result == null) return
            adapter.photos = result
        }
    }

    private fun loadAlbums(userId: Int): List<Album> {
        val url = URL("$LOAD_ALBUMS_URL?${Album.KEY_USER_ID}=$userId")
        val urlConnection = url.openConnection()
        val inputStreamReader = BufferedReader(
            InputStreamReader(urlConnection.getInputStream())
        )
        val builder = StringBuilder()
        var line = inputStreamReader.readLine()
        while (line != null) {
            builder.append(line)
            line = inputStreamReader.readLine()
        }
        return mapper.mapJsonToAlbumList(JSONArray(builder.toString()))
    }

    private fun loadPhotos(albumIds: List<Int>): List<Photo> {
        val idsToPhotoUrlBuilder = StringBuilder()
        for (i in albumIds.indices) {
            if (i == 0) {
                idsToPhotoUrlBuilder.append("?")
            } else {
                idsToPhotoUrlBuilder.append("&")
            }
            idsToPhotoUrlBuilder.append("${Photo.KEY_ALBUM_ID}=${albumIds[i]}")
        }
        val url = URL(LOAD_PHOTOS_URL + idsToPhotoUrlBuilder.toString())
        val urlConnection = url.openConnection()
        val inputStreamReader = BufferedReader(
            InputStreamReader(urlConnection.getInputStream())
        )
        val builder = StringBuilder()
        var line = inputStreamReader.readLine()
        while (line != null) {
            builder.append(line)
            line = inputStreamReader.readLine()
        }
        return mapper.mapJsonToPhotoList(JSONArray(builder.toString()))
    }

    companion object {

        private const val EXTRA_USER_ID = "extra_user_id"

        private const val LOAD_ALBUMS_URL = "https://jsonplaceholder.typicode.com/albums"
        private const val LOAD_PHOTOS_URL = "https://jsonplaceholder.typicode.com/photos"

        fun newIntent(context: Context, userId: Int): Intent {
            val intent = Intent(context, PhotosActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            return intent
        }
    }
}