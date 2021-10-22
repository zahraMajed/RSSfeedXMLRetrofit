package com.example.rssfeedxmlretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {

    private  val BASE_URL = "https://www.reddit.com/r/"
    lateinit var btnFetch: Button
    lateinit var tvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle = findViewById(R.id.tvTitle)
        btnFetch = findViewById(R.id.btnFetch)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(APIinterface::class.java).feed

        btnFetch.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                retrofit!!.enqueue(object : Callback<Feed?> {
                    override fun onResponse(call: Call<Feed?>, response: Response<Feed?>) {
                        val entries = response.body()!!.entrys
                        for (entry in entries!!) {
                            var text = tvTitle.text.toString()
                            tvTitle.text = text + "\n" + entry.title
                        }
                    }

                    override fun onFailure(call: Call<Feed?>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "An Error Occured", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })

    }
}