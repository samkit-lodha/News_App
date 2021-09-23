package com.example.vtr

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(val context :Context,val list : List<Article>) : RecyclerView.Adapter<MyAdapter.MyViewModel>() {
    class MyViewModel(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleNews = itemView.findViewById<TextView>(R.id.newsTitle)
        val descriptionNews = itemView.findViewById<TextView>(R.id.newsDescription)
        val authorNews = itemView.findViewById<TextView>(R.id.newsAuthor)
        val publishedAtNews = itemView.findViewById<TextView>(R.id.newsPublishedAt)
        val imageNews = itemView.findViewById<ImageView>(R.id.newsImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        return MyViewModel(LayoutInflater.from(context).inflate(R.layout.my_list_view,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        val art = list[position]
        Glide.with(context).load(art.urlToImage).into(holder.imageNews)
        holder.authorNews.text = art.author
        holder.titleNews.text = art.title
        holder.descriptionNews.text = art.description
        holder.publishedAtNews.text = art.publishedAt

        holder.itemView.setOnClickListener {
            Toast.makeText(context,art.title,Toast.LENGTH_SHORT).show()
            val intent = Intent(context,WebNewsActivity::class.java)
            intent.putExtra("URL",art.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}