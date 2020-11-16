package com.example.designapp.adapter




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.designapp.R
import com.example.designapp.data.model.Article
import kotlinx.android.synthetic.main.item_news.view.*


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
    private val differCalBack = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {

            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this@NewsAdapter, differCalBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvTitle.text=article.title
            tvDescription.text=article.description
            setOnClickListener{
                onItemClickListener?.let{
                    it(article,position)
                }
            }
        }
    }
    private var onItemClickListener:((Article , Int) ->Unit)?=null
    fun setOnItemClickListener (listener:(Article , Int) ->Unit){
        onItemClickListener=listener
    }
}
