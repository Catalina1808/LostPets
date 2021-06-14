package com.example.lostpet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.lostpet.R
import com.example.lostpet.adapters.HttpRequestAdapter
import com.example.lostpet.models.Constants.Companion.OWNER
import com.example.lostpet.models.Constants.Companion.URL
import com.example.lostpet.models.RandomPhoto
import com.example.lostpet.models.VolleyConfigSingleton
import org.json.JSONObject
import java.util.*
import kotlin.random.Random
import kotlin.collections.ArrayList

class RandomPictureActivity : AppCompatActivity() {
    val photos:ArrayList<RandomPhoto> = ArrayList<RandomPhoto>()
    var adapter: HttpRequestAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_picture)

        for (index in 0 until 10) {
            val random= Random.nextInt(0, 500);
            getPhotos("https://loremflickr.com/json/320/240/pets?random=$random")
        }
        setUpRecyclerView()
    }

   fun setUpRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.rv_random)
        val layoutManager: GridLayoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        photos.clear()
        adapter = HttpRequestAdapter(photos)

        recyclerView.adapter = adapter

    }

    fun getPhotos(url: String) {
        val volleyConfigSingleton = VolleyConfigSingleton.getInstance(this.applicationContext)
        val queue = volleyConfigSingleton.requestQueue
        val getPhotosRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { responseJson ->
                handlePhotosResponse(responseJson)
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    this,
                    "ERROR: get photos failed with error: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        )
        queue.add(getPhotosRequest)
    }


    fun handlePhotosResponse(response: String) {
        val photosJSONObject = JSONObject(response)

            val photoJSON: JSONObject? = photosJSONObject as? JSONObject
            photoJSON?.let {
                val owner = photoJSON.getString(OWNER)
                val url= photoJSON.getString(URL)

                val photo: RandomPhoto = RandomPhoto(owner, url)
                photos.add(photo)

        }
        adapter?.notifyDataSetChanged()
    }
}