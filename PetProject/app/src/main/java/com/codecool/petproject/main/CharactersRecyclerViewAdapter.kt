package com.codecool.petproject.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codecool.petproject.databinding.ListItemBinding
import com.codecool.petproject.details.DetailsActivity
import com.codecool.petproject.model.Character
import com.codecool.petproject.util.getProgressDrawable
import com.codecool.petproject.util.loadImage

class CharactersRecyclerViewAdapter(var characters: ArrayList<Character>): RecyclerView.Adapter<CharactersRecyclerViewAdapter.CharacterViewHolder>() {

    private lateinit var listItemBinding: ListItemBinding

    fun updateCharacters(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersRecyclerViewAdapter.CharacterViewHolder {
        listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(listItemBinding)
//        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharactersRecyclerViewAdapter.CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    class CharacterViewHolder(view: ListItemBinding): RecyclerView.ViewHolder(view.root) {

        private val imageView = view.characterImage
        private val characterName = view.characterName
        private val characterSpecies = view.characterSpecies

        private var progressDrawable = getProgressDrawable(view.root.context)

        fun bind(character: Character) {
            imageView.loadImage(character.image, progressDrawable)
            characterName.text = character.name
            characterSpecies.text = character.species?.capitalize()

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailsActivity::class.java)
                intent.putExtra("character", character)
                itemView.context.startActivity(intent)
            }
        }
    }
}