package com.hito.nikolay.testhedgehog

import android.app.ActionBar
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hito.nikolay.testhedgehog.model.JokesData
import com.hito.nikolay.testhedgehog.model.Value
import com.hito.nikolay.testhedgehog.recyclerView.JokesAdapter
import com.hito.nikolay.testhedgehog.recyclerView.JokesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokesFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var jokesData: List<Value>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jokes, container, false)
        activity?.setTitle(R.string.jokes)

        view.findViewById<Button>(R.id.getJokesButton).setOnClickListener {
            val amountOfJokes =
                view.findViewById<EditText>(R.id.editTextAmountOfJokes).text.toString()

            // Загрузка даннных
            val retrofit = Retrofit.Builder()
                .baseUrl(requestJokesURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(JokesService::class.java)
            val call = service.getJokesJSON(
                amountOfJokes
            )

            call.enqueue(object : Callback<JokesData> {
                override fun onResponse(call: Call<JokesData>, response: Response<JokesData>) {
                    if (response.isSuccessful) {
                        jokesData = response.body()?.value
                        if (jokesData != null) {
                            setRecyclerView(jokesData, view = view)
                        } else {
                            Snackbar.make(it, "Ошибка! Не удалось получить шутки :(", 10000)
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<JokesData>, t: Throwable) {
                    Snackbar.make(it, t.message.toString(), 10000).show()
                }
            })
        }

        return view
    }

    private fun setRecyclerView(dataset: List<Value>?, view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.jokesRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(view.context)
        recyclerView?.adapter = dataset?.let { JokesAdapter(it) }
        recyclerView?.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        var itemDecorator = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(view.context.resources.getDrawable(R.drawable.divider_drawable));
        recyclerView?.addItemDecoration(itemDecorator)
    }
}