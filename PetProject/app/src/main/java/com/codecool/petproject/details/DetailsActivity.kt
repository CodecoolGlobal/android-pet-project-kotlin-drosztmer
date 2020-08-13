package com.codecool.petproject.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codecool.petproject.R
import com.codecool.petproject.databinding.ActivityDetailsBinding
import com.codecool.petproject.model.Character
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var activityDetailsBinding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        activityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = activityDetailsBinding.root
        setContentView(view)

        setViews()
    }

    private fun setViews() {
        val character: Character = intent.getSerializableExtra("character") as Character
        activityDetailsBinding.currentName.text = character.name
        Glide.with(this).load(character.image).into(activityDetailsBinding.detailsImage)
        activityDetailsBinding.currentSpecies.text = character.species?.capitalize()
        activityDetailsBinding.currentGender.text = character.gender?.capitalize()
        activityDetailsBinding.currentHeight.text = character.height.toString()
    }

    override fun onStart() {
        super.onStart()
        stars_details.onStart()
    }

    override fun onStop() {
        super.onStop()
        stars_details.onStop()
    }
}