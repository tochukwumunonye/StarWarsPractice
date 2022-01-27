package com.tochukwu.starwarspractice.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import com.tochukwu.starwarspractice.databinding.CharactersRowBinding
import com.tochukwu.starwarspractice.util.loadImageOrDefault
import com.tochukwu.starwarspractice.util.loadOrGone
import javax.inject.Inject

class DisneyAdapter @Inject constructor() : androidx.recyclerview.widget.ListAdapter<PosterDtoItem, DisneyAdapter.PosterViewHolder>(DIFF_COMPARATOR){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val binding = CharactersRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PosterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
            val currentItem = getItem(position)

            if (currentItem != null) {
                holder.bind(currentItem)
            }
    }

    inner class PosterViewHolder(private val binding: CharactersRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(app: PosterDtoItem){
            binding.apply{

                paymentName.text = app.name
                Glide.with(itemView).load(app.poster).into(logo)

                setOnClickListener(app)

            }
        }

        private fun setOnClickListener(app: PosterDtoItem){
            binding.detailItem.setOnClickListener { view ->
                navigateToDetail(app, view)
            }
        }

        private fun navigateToDetail(app: PosterDtoItem, view: View) {
            val directions = CharacterFragmentDirections.actionCharacterFragmentToDisneyDetail(app)
            view.findNavController().navigate(directions)
        }


    }

   companion object {
       private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<PosterDtoItem>() {
           override fun areItemsTheSame(oldItem: PosterDtoItem, newItem: PosterDtoItem): Boolean {
               return oldItem.name == newItem.name
           }

           override fun areContentsTheSame(
               oldItem: PosterDtoItem,
               newItem: PosterDtoItem
           ): Boolean {
               return oldItem == newItem
           }
       }
   }

}

