package com.example.vtr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vtr.databinding.FragmentSportsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsFragment : Fragment() {

    private lateinit var binding: FragmentSportsBinding
    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private var articleList = mutableListOf<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sports,container,false)
        recyclerView = binding.recyclerViewSports
        adapter = MyAdapter(requireContext(),articleList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getNews()
        return binding.root
    }

    private fun getNews() {
        val news : Call<News> = NewsServices.newsInterface.getSports(1)
        news.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if(response.isSuccessful()){
                    val rNews = response.body()
                    if(rNews!=null){
                        articleList.addAll(rNews.articles)
                        adapter.notifyDataSetChanged()
                    }
                }
                else{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Sammy","Error",t)
            }

        })
    }
}