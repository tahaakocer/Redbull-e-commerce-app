package com.techtitans.ecommerce3.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.techtitans.ecommerce3.Activity.DetailActivity
import com.techtitans.ecommerce3.Model.AtletModel
import com.techtitans.ecommerce3.databinding.ViewholderAthleteBinding

class AthleteApadter(val items: MutableList<AtletModel>) :
    RecyclerView.Adapter<AthleteApadter.Viewholder>() {
    private var context: Context? = null

    class Viewholder(val binding: ViewholderAthleteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AthleteApadter.Viewholder {
        context = parent.context
        val binding =
            ViewholderAthleteBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AthleteApadter.Viewholder, position: Int) {
        holder.binding.sporTxt.text = items[position].spor
        holder.binding.nameTxt.text = items[position].name

        val requestOption = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].imageUrl)
            .apply(requestOption)
            .into(holder.binding.picAthlete)

    }
    }