package com.bookslibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bookslibrary.data.model.FavBookItem
import com.bookslibrary.databinding.ItemFavBookBinding

/**
 * Created by Divya V on 20-10-2024.
 */

class FavoriteBooksAdapter(val booksList:ArrayList<FavBookItem>): RecyclerView.Adapter<FavoriteBooksAdapter.FavBookViewHolder>() {

    class FavBookViewHolder( val binding: ItemFavBookBinding): ViewHolder(binding.root) {
        fun bind(bookItem: FavBookItem){
            binding.titleTV.text="Title : "+bookItem.book.title
            binding.releaseDateTV.text="Release Date : "+bookItem.book.releaseDate.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavBookViewHolder {
        val itemBookBinding= ItemFavBookBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return FavBookViewHolder(itemBookBinding)
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    override fun onBindViewHolder(holder: FavBookViewHolder, position: Int) {
        holder.bind(booksList[position])


    }
}

