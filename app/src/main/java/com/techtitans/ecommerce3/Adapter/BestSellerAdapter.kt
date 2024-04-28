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
import com.techtitans.ecommerce3.Model.ItemsModel
import com.techtitans.ecommerce3.databinding.ViewholderBestSellerBinding


class BestSellerAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<BestSellerAdapter.Viewholder>() {
    private var context: Context? = null

    class Viewholder(val binding: ViewholderBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestSellerAdapter.Viewholder {
        context = parent.context
        val binding =
            ViewholderBestSellerBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BestSellerAdapter.Viewholder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "₺" + items[position].price.toString()
        holder.binding.ratingTxt.text = items[position].rating.toString()

        val requestOption = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].thumbnail)
            .apply(requestOption)
            .into(holder.binding.picBestSeller)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}