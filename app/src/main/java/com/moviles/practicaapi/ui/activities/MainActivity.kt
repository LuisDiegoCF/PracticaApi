package com.moviles.practicaapi.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicaapi.R
import com.moviles.practicaapi.repositories.PostRepository
import com.moviles.practicaapi.models.Post
import com.moviles.practicaapi.ui.adapters.PostAdapter

class MainActivity : AppCompatActivity(), PostRepository.PostListListener, PostRepository.PostByIdListener {
    private lateinit var btnCallApi: Button
    private lateinit var lstPosts: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCallApi = findViewById(R.id.btnCallApi)
        lstPosts = findViewById(R.id.lstPosts)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnCallApi.setOnClickListener {
            callPostListApi()
        }
    }

    private fun callPostListApi() {
        PostRepository.getPosts(this)
    }

    private fun callPostApi() {
        PostRepository.getPost(1, this)
    }
//#region PostListApi
    override fun onPostListSuccess(posts: List<Post>) {
        val adapter = PostAdapter(posts as ArrayList<Post>)
        lstPosts.layoutManager = LinearLayoutManager(this@MainActivity)
        lstPosts.adapter = adapter
    }

    override fun onPostListFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }

    override fun onPostByIdSuccess(post: Post) {
        Toast.makeText(this@MainActivity, post.title, Toast.LENGTH_SHORT).show()
    }

    override fun onPostByIdFailure(t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
    }
    //#endregion
}