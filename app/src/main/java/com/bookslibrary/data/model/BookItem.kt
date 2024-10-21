package com.bookslibrary.data.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Books")
data class BookItem(@PrimaryKey(autoGenerate = true)  val id:Int=0,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("cover")
        val cover: String? = null,
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("index")
        val index: Int? = null,
        @SerializedName("number")
        val number: Int? = null,
        @SerializedName("originalTitle")
        val originalTitle: String? = null,
        @SerializedName("pages")
        val pages: Int? = null,
        @SerializedName("releaseDate")
        val releaseDate: String? = null

    )



@Entity(tableName = "FavoriteBooks")
data class FavBookItem(@PrimaryKey(autoGenerate = true)
       val fav_id: Int,
        @Embedded val book: BookItem,
        val savedTimestamp: Long
)