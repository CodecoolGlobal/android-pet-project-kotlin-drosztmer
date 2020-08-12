package com.codecool.petproject.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.codecool.petproject.R
import com.codecool.petproject.model.Character
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val character: Character = intent.getSerializableExtra("character") as Character
        current_name.text = character.name
        Glide.with(this).load(character.image).into(details_image)
        current_species.text = character.species
        current_gender.text = character.gender
        current_height.text = character.height.toString()
    }
}