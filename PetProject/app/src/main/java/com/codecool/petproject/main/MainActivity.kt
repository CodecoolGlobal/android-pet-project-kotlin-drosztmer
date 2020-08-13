package com.codecool.petproject.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.codecool.petproject.R
import com.codecool.petproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val charactersRecyclerViewAdapter = CharactersRecyclerViewAdapter(arrayListOf())

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)

        viewModel.refresh()

        activityMainBinding.charactersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charactersRecyclerViewAdapter
        }

        activityMainBinding.swipeRefreshLayout.setOnRefreshListener {
            swipe_refresh_layout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.characters.observe(this, Observer { characters ->
            characters?.let {
                activityMainBinding.charactersList.visibility = View.VISIBLE
                charactersRecyclerViewAdapter.updateCharacters(it)
            }
        })

        viewModel.characterLoadError.observe(this, Observer { isError ->
            isError?.let {
                activityMainBinding.listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    activityMainBinding.loadingView.visibility = View.VISIBLE
                    activityMainBinding.listError.visibility = View.GONE
                    activityMainBinding.charactersList.visibility = View.GONE
                } else {
                    activityMainBinding.loadingView.visibility = View.GONE
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        activityMainBinding.stars.onStart()
    }

    override fun onStop() {
        super.onStop()
        activityMainBinding.stars.onStop()
    }
}