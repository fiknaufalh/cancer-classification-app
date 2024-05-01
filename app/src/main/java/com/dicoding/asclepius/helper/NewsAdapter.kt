package com.dicoding.asclepius.helper

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.remote.response.ArticlesItem

class NewsAdapter: ListAdapter<ArticlesItem, NewsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
    ) {
        private val newsImage: ImageView = itemView.findViewById(R.id.ivNews)
        private val newsTitle: TextView = itemView.findViewById(R.id.tvNewsTitle)
        private val newsDesc: TextView = itemView.findViewById(R.id.tvNewsDesc)
        private val newsPublishedAt: TextView = itemView.findViewById(R.id.tvPublishedAt)

        fun bind(article: ArticlesItem) {
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .into(newsImage)

            newsTitle.text = article.title
            newsDesc.text = article.description

            val formattedDate = DateConverter.formatDate(article.publishedAt.toString())
            newsPublishedAt.text = itemView.context
                .getString(R.string.published_at_custom, formattedDate)


            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}