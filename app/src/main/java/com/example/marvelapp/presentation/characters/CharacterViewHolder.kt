package com.example.marvelapp.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import antoniojoseuchoa.com.core.domain.model.Character
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.marvelapp.databinding.ItemCharacterBinding

class CharacterViewHolder(binding: ItemCharacterBinding) : ViewHolder(binding.root) {

    var tvNome: TextView = binding.tvNameCharacter
    var ivCharacter: ImageView = binding.ivCharacter

    fun bind(character: Character) {
        tvNome.text = character.name
        Glide.with(itemView).load(character.image).into(ivCharacter)
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
               val inflate = LayoutInflater.from(parent.context)
               val binding = ItemCharacterBinding.inflate(inflate)
               return CharacterViewHolder(binding)
        }
    }
}
