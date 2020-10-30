package ru.sumin.usersphoto

import org.json.JSONArray
import org.json.JSONObject
import ru.sumin.usersphoto.pojo.Album
import ru.sumin.usersphoto.pojo.Photo
import ru.sumin.usersphoto.pojo.User

class Mapper {

    fun mapJsonToUserList(jsonObject: JSONObject): List<User> {
        val users = mutableListOf<User>()
        val jsonArray = JSONArray(jsonObject)
        for (i in 0 until jsonArray.length()) {
            val json: JSONObject = jsonArray.getJSONObject(i)
            val id = json.getInt(User.KEY_ID)
            val name = json.getString(User.KEY_NAME)
            val user = User(id, name)
            users.add(user)
        }
        return users
    }

    fun mapJsonToPhotoList(jsonObject: JSONObject): List<Photo> {
        val photos = mutableListOf<Photo>()
        val jsonArray = JSONArray(jsonObject)
        for (i in 0 until jsonArray.length()) {
            val json: JSONObject = jsonArray.getJSONObject(i)
            val id = json.getInt(Photo.KEY_ID)
            val albumId = json.getInt(Photo.KEY_ALBUM_ID)
            val title = json.getString(Photo.KEY_TITLE)
            val url = json.getString(Photo.KEY_URL)
            val photo = Photo(albumId, id, title, url)
            photos.add(photo)
        }
        return photos
    }

    fun mapJsonToAlbumList(jsonObject: JSONObject): List<Album> {
        val albums = mutableListOf<Album>()
        val jsonArray = JSONArray(jsonObject)
        for (i in 0 until jsonArray.length()) {
            val json: JSONObject = jsonArray.getJSONObject(i)
            val userId = json.getInt(Album.KEY_USER_ID)
            val id = json.getInt(Album.KEY_ID)
            val title = json.getString(Album.KEY_TITLE)
            val album = Album(userId, id, title)
            albums.add(album)
        }
        return albums
    }
}
