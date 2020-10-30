package ru.sumin.usersphoto.pojo

data class User(
        val id: Int,
        val name: String
) {
    companion object {
        const val KEY_ID = "id"
        const val KEY_NAME = "name"
    }
}
