package com.techtitans.ecommerce3.Activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.techtitans.ecommerce3.Adapter.CartAdapter
import com.techtitans.ecommerce3.Helper.ChangeNumberItemsListener
import com.techtitans.ecommerce3.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()

        binding.checkOutBtn.setOnClickListener {

            managmentCart.clearCart()

            initCartList()

            calculateCart()

        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0
        tax = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100

        with(binding) {
            totalFeeTxt.text = "₺$itemTotal"
            taxTxt.text = "₺$tax"
            deliveryTxt.text = "₺$delivery"
            totalTxt.text = "₺$total"
        }
    }

    private fun initCartList() {
        binding.cartView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.adapter =
            CartAdapter(managmentCart.getListCart(), this, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            })
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {  startActivity(Intent(this,MainActivity::class.java)) }
    }
}