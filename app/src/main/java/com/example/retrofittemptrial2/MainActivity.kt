package com.example.retrofittemptrial2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.retrofittemptrial2.databinding.ActivityMainBinding
import com.example.retrofittemptrial2.model.api_Interface
import com.example.retrofittemptrial2.model.mydataitems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding!!.root)


//        binding!!.btnLoadData.setOnClickListener {
            Toast.makeText(application, "hehe , btn is working. ", Toast.LENGTH_SHORT).show()

            val BASE_URL = "https://jsonplaceholder.typicode.com/"


            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(api_Interface::class.java)


            val retrofit_data = retrofitBuilder.getdata()


            retrofit_data.enqueue(object : Callback<List<mydataitems>?> {
                override fun onResponse(
                    call: Call<List<mydataitems>?>,
                    response: Response<List<mydataitems>?>
                ) {


                    val responseBody = response.body()!!

                    val MyStringBuidler = StringBuilder()

                    for (my_fetched_data in responseBody) {
                        MyStringBuidler.append(my_fetched_data.id)
                    }

                    Log.d("mytag", "lets see this works or not ")
                    binding!!.loadFetchedDataHere.text = MyStringBuidler


                }

                override fun onFailure(call: Call<List<mydataitems>?>, t: Throwable) {

                    Log.d("retrofit_issue", "onFailure : " + t.message)

                }
            })
        }

    }
//}

