package ru.sumin.usersphoto

import org.json.JSONArray
import org.json.JSONObject
import ru.sumin.usersphoto.pojo.Album
import ru.sumin.usersphoto.pojo.Photo
import ru.sumin.usersphoto.pojo.User

class Mapper {

    fun mapJsonToUserList(jsonArray: JSONArray): List<User> {
        val users = mutableListOf<User>()
        for (i in 0 until jsonArray.length()) {
            val json: JSONObject = jsonArray.getJSONObject(i)
            val id = json.getInt(User.KEY_ID)
            val name = json.getString(User.KEY_NAME)
            val user = User(id, name)
            users.add(user)
        }
        return users
    }

    fun mapJsonToPhotoList(jsonArray: JSONArray): List<Photo> {
        val photos = mutableListOf<Photo>()
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

    fun mapJsonToAlbumList(jsonArray: JSONArray): List<Album> {
        val albums = mutableListOf<Album>()
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
