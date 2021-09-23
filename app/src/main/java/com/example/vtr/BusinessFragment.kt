package com.example.vtr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vtr.databinding.FragmentBusinessBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusinessFragment : Fragment() {

    private lateinit var binding: FragmentBusinessBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var articleList = mutableListOf<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_business,container,false)
        recyclerView = binding.recyclerViewBusiness

        adapter = MyAdapter(requireContext(),articleList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        getNews()
        return binding.root
    }

    private fun getNews() {
        val news : Call<News> = NewsServices.newsInterface.getBusiness(1)
        news.enqueue(object :Callback<News>{
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
                TODO("Not yet implemented")
            }

        })
    }
}