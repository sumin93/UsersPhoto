package ru.sumin.usersphoto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PhotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        val id = intent.getIntExtra(EXTRA_USER_ID, -1)
        println("USER_ID $id")
    }

    companion object {
        private const val EXTRA_USER_ID = "extra_user_id"

        fun newIntent(context: Context, userId: Int): Intent {
            val intent = Intent(context, PhotosActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, userId)
            return intent
        }
    }
}