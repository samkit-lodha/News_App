package com.example.vtr

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/everything?q=apple&from=2021-09-18&to=2021-09-18&sortBy=popularity&apiKey=d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/top-headlines?country=in&category=entertainment&apiKey=d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/everything?q=tesla&from=2021-08-22&sortBy=publishedAt&apiKey=d156861d9e7b48a4ab08d26e93d40c3e

//https://newsapi.org/v2/everything?q=bitcoin&apiKey=d156861d9e7b48a4ab08d26e93d40c3e
const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "d156861d9e7b48a4ab08d26e93d40c3e"

interface NewsInterface{
    @GET("top-headlines?apiKey=$API_KEY")
    fun getHeadline(@Query("country") country: String,@Query("page") page: Int) : Call<News>

    @GET("everything?domains=wsj.com&apiKey=$API_KEY")
    fun getEverything(@Query("page") page: Int) : Call<News>

    @GET("top-headlines?country=us&category=business&apiKey=$API_KEY")
    fun getBusiness(@Query("page") page: Int) : Call<News>

    @GET("top-headlines?category=entertainment&apiKey=$API_KEY")
    fun getEntertain(@Query("country") country : String, @Query("page") page : Int) : Call<News>

    @GET("top-headlines?country=in&category=sports&apiKey=$API_KEY")
    fun getSports(@Query("page") page : Int) : Call<News>

    @GET("everything?q=bitcoin&apiKey=$API_KEY")
    fun getCrypto(@Query("page") page : Int) : Call<News>

    @GET("everything?q=apple&from=2021-09-18&to=2021-09-18&sortBy=popularity&apiKey=$API_KEY")
    fun getTech(@Query("page") page : Int) : Call<News>
}

object NewsServices {
    val newsInterface : NewsInterface

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        newsInterface = retrofit.create(NewsInterface::class.java)
    }
}