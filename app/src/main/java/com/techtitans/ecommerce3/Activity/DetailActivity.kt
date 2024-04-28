package com.techtitans.ecommerce3.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ManagmentCart
import com.techtitans.ecommerce3.Adapter.PicListAdapter
import com.techtitans.ecommerce3.Model.ItemsModel
import com.techtitans.ecommerce3.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        getBundle()

    }

    private fun initList() {

        val colorList = ArrayList<String>()
        for (imageUrl in item.thumbnail) {
            colorList.add(imageUrl)
        }

        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

        binding.picList.adapter = PicListAdapter(colorList, binding.picMain)
        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!
        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text = "TL" + item.price
        binding.ratingTxt.text = "${item.rating} Rating"
        binding.SellerNameTxt.text = item.sellerName


        binding.AddToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            managmentCart.insertItems(item)
        }
        binding.backBtn.setOnClickListener { finish() }
        binding.CartBtn.setOnClickListener {

            //silme işlemi
        }
        binding.msgToSellerBtn.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.setData(Uri.parse("sms:" + item.sellerTell))
            sendIntent.putExtra("sms_body", "type your message")
            startActivity(intent)
        }

        binding.calToSellerBtn.setOnClickListener {
            val phone = item.sellerTell.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }

    }
}