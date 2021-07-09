package com.hito.nikolay.testhedgehog.recyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hito.nikolay.testhedgehog.R
import com.hito.nikolay.testhedgehog.model.Value

class ViewHolderJokes(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textViewCardJoke: TextView = itemView.findViewById(R.id.textViewOfCardJoke)

    fun bind(data: Value) {
        textViewCardJoke.text = data.joke
    }
}