package com.example.colocviu2

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
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
        val ps = ProgressDialog.show(this, "Loading", "Wait while loading...")
        val handler = Handler()
        handler.postDelayed(Runnable { ps.dismiss() }, 2000)
        progressBar.visibility = View.INVISIBLE

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(textList)
    }

    private fun getElement() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.boredapi.com/api/activity/")
            .build()

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
                        textList.add(entity)
                    }
                }
            }
        })
    }

    private fun getElement(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()


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
                        textList.add(entity)
                    }
                }
            }
        })
    }

    private fun postToList(url: String) {
        for (i in 1..10) {
            getElement(url)
        }
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
        val urlList = arrayOf(
            "https://www.boredapi.com/api/activity?type=education",
            "https://www.boredapi.com/api/activity?type=recreational",
            "https://www.boredapi.com/api/activity?type=social",
            "https://www.boredapi.com/api/activity?type=charity",
            "https://www.boredapi.com/api/activity?type=cooking",
            "https://www.boredapi.com/api/activity?type=music",
            "https://www.boredapi.com/api/activity/"
        )

        textList = mutableListOf()

        when (item.itemId) {
            R.id.nav_element_1 -> {
                val pe = ProgressDialog.show(this, "Loading", "Wait while loading...")
                val handler = Handler()

                postToList(urlList[0])

                handler.postDelayed({
                    this.title = "Education";
                    item.title = "random"

                    val recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = RecyclerAdapter(textList)

                    Toast.makeText(this, "Education selected", Toast.LENGTH_SHORT)
                        .show()
                    pe.dismiss()
                }, 2_000)

            }
            R.id.nav_element_2 -> {
                val pr = ProgressDialog.show(this, "Loading", "Wait while loading...")
                val handler = Handler()

                postToList(urlList[1])

                handler.postDelayed({
                    this.title = "Recreational";
                    item.title = "random"

                    val recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = RecyclerAdapter(textList)

                    Toast.makeText(this, "Recreational selected", Toast.LENGTH_SHORT)
                        .show()
                    pr.dismiss()
                }, 2_000)
            }
            R.id.nav_element_3 -> {
                val ps = ProgressDialog.show(this, "Loading", "Wait while loading...")
                val handler = Handler()

                postToList(urlList[2])

                handler.postDelayed({
                    this.title = "Social";
                    item.title = "random"

                    val recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = RecyclerAdapter(textList)

                    Toast.makeText(this, "Social selected", Toast.LENGTH_SHORT)
                        .show()
                    ps.dismiss()
                }, 2_000)

            }

            R.id.nav_element_4 -> {
                val pc = ProgressDialog.show(this, "Loading", "Wait while loading...")
                val handler = Handler()

                postToList(urlList[3])

                handler.postDelayed({
                    this.title = "Charity";
                    item.title = "random"

                    val recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = RecyclerAdapter(textList)

                    Toast.makeText(this, "Charity selected", Toast.LENGTH_SHORT)
                        .show()
                    pc.dismiss()
                }, 2_000)
            }
            R.id.nav_element_5 -> {
                val pcc = ProgressDialog.show(this, "Loading", "Wait while loading...")
                val handler = Handler()

                postToList(urlList[4])

                handler.postDelayed({
                    this.title = "Cooking";
                    item.title = "random"

                    val recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = RecyclerAdapter(textList)

                    Toast.makeText(this, "Cooking selected", Toast.LENGTH_SHORT)
                        .show()
                    pcc.dismiss()
                }, 2_000)
            }
            R.id.nav_element_6 -> {
                val pm = ProgressDialog.show(this, "Loading", "Wait while loading...")
                val handler = Handler()
                postToList(urlList[5])

                handler.postDelayed({
                    this.title = "Music";
                    item.title = "random"

                    val recyclerView = findViewById<RecyclerView>(R.id.rv_recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.adapter = RecyclerAdapter(textList)

                    Toast.makeText(this, "Music selected", Toast.LENGTH_SHORT)
                        .show()
                    pm.dismiss()
                }, 2_000)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}