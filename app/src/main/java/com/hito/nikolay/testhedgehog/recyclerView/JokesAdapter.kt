package com.hito.nikolay.testhedgehog.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hito.nikolay.testhedgehog.R
import com.hito.nikolay.testhedgehog.model.Value

class JokesAdapter(private val mDataset: List<Value>) : RecyclerView.Adapter<ViewHolderJokes>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderJokes {
        val viewService: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_jokes, parent, false)
        return ViewHolderJokes(viewService)
    }

    override fun onBindViewHolder(holder: ViewHolderJokes, position: Int) {
        holder.textViewCardJoke.text = mDataset[position].joke
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}

