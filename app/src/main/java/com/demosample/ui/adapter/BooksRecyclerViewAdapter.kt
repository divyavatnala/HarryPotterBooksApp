package com.demosample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.demosample.data.model.BookItem
import com.demosample.databinding.ItemBookBinding
import com.demosample.ui.listeners.OnItemClickListener

/**
 * Created by Divya V on 20-10-2024.
 */
class BooksRecyclerViewAdapter(val booksList:ArrayList<BookItem>, val listener:OnItemClickListener): RecyclerView.Adapter<BooksRecyclerViewAdapter.BooksViewHolder>() {

    class BooksViewHolder( val binding: ItemBookBinding):ViewHolder(binding.root) {
        fun bind(bookItem: BookItem){
            binding.titleTV.text="Title : "+bookItem.title
            binding.noPagesTV.text="No of Pages :"+bookItem.pages.toString()
            binding.releaseDateTV.text="Release Date :"+bookItem.releaseDate.toString()
            Glide.with(itemView.context).load(bookItem.cover)
                .into(binding.bookIV)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val itemBookBinding= ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return BooksViewHolder(itemBookBinding)
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(booksList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(position,booksList[position])
        }
        holder.binding.favIV.setOnClickListener {
            listener.onFavClick(position,booksList[position])
        }

    }
}