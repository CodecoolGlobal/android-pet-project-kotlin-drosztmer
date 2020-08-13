package com.codecool.petproject.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codecool.petproject.R
import com.codecool.petproject.details.DetailsActivity
import com.codecool.petproject.model.Character
import com.codecool.petproject.util.getProgressDrawable
import com.codecool.petproject.util.loadImage
import kotlinx.android.synthetic.main.list_item.view.*

class CharactersRecyclerViewAdapter(var characters: ArrayList<Character>): RecyclerView.Adapter<CharactersRecyclerViewAdapter.CharacterViewHolder>() {

    fun updateCharacters(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersRecyclerViewAdapter.CharacterViewHolder {
        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharactersRecyclerViewAdapter.CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView = view.character_image
        private val characterName = view.character_name
        private val character_species = view.character_species

        private var progressDrawable = getProgressDrawable(view.context)

        fun bind(character: Character) {
            imageView.loadImage(character.image, progressDrawable)
            characterName.text = character.name
            character_species.text = character.species

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                intent.putExtra("character", character)
                itemView.context.startActivity(intent)
            }
        }
    }
}