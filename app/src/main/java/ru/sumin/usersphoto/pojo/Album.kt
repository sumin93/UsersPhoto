package ru.sumin.usersphoto.pojo

data class Album(
    val userId: Int,
    val id: Int,
    val title: String
) {
    companion object {
        const val KEY_USER_ID = "userId"
        const val KEY_ID = "id"
        const val KEY_TITLE = "title"
    }
}
