package com.ikajihan.app.animals

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ikajihan.app.animals.databinding.ItemCardviewAnimalBinding

class ListAnimalsAdapter(private val listAnimals: ArrayList<Animals>) : RecyclerView.Adapter<ListAnimalsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemCardviewAnimalBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, habitat, characteristics, photo) = listAnimals[position]
        holder.binding.tvAnimalName.text = name
//        holder.binding.tvDescription.text = description
        holder.binding.tvAnimalHabitat.text = habitat
        holder.binding.tvAnimalCharacteristics.text = characteristics
        holder.binding.imgItemPhoto.setImageResource(photo)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailAnimalsActivity::class.java).apply {
                putExtra("animal", listAnimals[holder.adapterPosition])
            }
            context.startActivity(intent)
            onItemClickCallBack.onItemClicked(listAnimals[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listAnimals.size

    class ListViewHolder(var binding: ItemCardviewAnimalBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallBack {
        fun onItemClicked(data: Animals)
    }
}
