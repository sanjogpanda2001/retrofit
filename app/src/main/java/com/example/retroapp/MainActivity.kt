package com.example.retroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //RetrofitBuilder
        val retrofit=Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/").build()

        val jsonplaceholderapi=retrofit.create(JsonPlaceHolderApi::class.java)

      val mycall = jsonplaceholderapi.getUsers()

        mycall.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val Users: List<User>? = response.body()
                val stringbuilder=StringBuilder()
                if (Users != null) {
                    for (user in Users){
                        stringbuilder.append(user.username)
                        stringbuilder.append("\n")
                        stringbuilder.append(user.email_user)
                        stringbuilder.append("\n")
                        stringbuilder.append(user.id)
                        stringbuilder.append("\n")
                        stringbuilder.append(user.name)
                        stringbuilder.append("\n")
                        stringbuilder.append("\n")
                    }
                }
                var text=findViewById<TextView>(R.id.textView_data)
                text.text=stringbuilder


            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("error",t.message.toString())
            }

        })
    }
}