package ru.sumin.usersphoto.pojo

data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String
) {
    companion object {
        const val KEY_ALBUM_ID = "albumId"
        const val KEY_ID = "id"
        const val KEY_TITLE = "title"
        const val KEY_URL = "url"
    }
}
