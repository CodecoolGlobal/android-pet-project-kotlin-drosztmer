package com.codecool.petproject.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codecool.petproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val charactersRecyclerViewAdapter = CharactersRecyclerViewAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.refresh()

        characters_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charactersRecyclerViewAdapter
        }

        swipe_refresh_layout.setOnRefreshListener {
            swipe_refresh_layout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.characters.observe(this, Observer { characters ->
            characters?.let {
                characters_list.visibility = View.VISIBLE
                charactersRecyclerViewAdapter.updateCharacters(it)
            }
        })

        viewModel.characterLoadError.observe(this, Observer { isError ->
            isError?.let {
                list_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    loading_view.visibility = View.VISIBLE
                    list_error.visibility = View.GONE
                    characters_list.visibility = View.GONE
                } else {
                    loading_view.visibility = View.GONE
                }
            }
        })
    }
}