package com.example.colocviu2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import java.io.IOException
import java.io.ObjectInput

class MainActivity : AppCompatActivity() {

    private var textList = mutableListOf<Entity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        progressBar.visibility = View.VISIBLE
        postToList()
        Thread.sleep(3_000)
        progressBar.visibility = View.INVISIBLE

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(textList)
    }

    private fun getElement() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.boredapi.com/api/activity/")
            .build()

        // Coroutines not supported directly, use the basic Callback way:
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val stringResponse = response.body?.string()
                    if (stringResponse != null) {
                        println(stringResponse)
                        val entity = Entity(stringResponse, false)
                        //Thread.sleep(1000)
                        textList.add(entity)
                    }
                }
            }
        })

    }

    private fun postToList() {
        for (i in 1..10) {
            getElement()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> Toast.makeText(this, "Delete selected", Toast.LENGTH_SHORT).show()
            R.id.nav_favorite -> Toast.makeText(this, "Favorite selected", Toast.LENGTH_SHORT)
                .show()
            R.id.nav_element_1 -> {
                this.title = "Education";
                item.title = "random"
                Toast.makeText(this, "Education selected", Toast.LENGTH_SHORT)
                    .show()
            }
            R.id.nav_element_2 -> Toast.makeText(this, "Recreational selected", Toast.LENGTH_SHORT)
                .show()
            R.id.nav_element_3 -> Toast.makeText(this, "Social selected", Toast.LENGTH_SHORT).show()
            R.id.nav_element_4 -> Toast.makeText(this, "Charity selected", Toast.LENGTH_SHORT)
                .show()
            R.id.nav_element_5 -> Toast.makeText(this, "Cooking selected", Toast.LENGTH_SHORT)
                .show()
            R.id.nav_element_6 -> Toast.makeText(this, "Music selected", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}