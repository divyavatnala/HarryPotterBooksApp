package com.demosample.ui.listeners

import com.demosample.data.model.BookItem

/**
 * Created by Divya V on 20-10-2024.
 */
interface OnItemClickListener {
    fun onItemClick(position:Int, item: BookItem)
    fun onFavClick(position:Int, item:BookItem)
}