package com.techtitans.ecommerce3.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.techtitans.ecommerce3.databinding.ActivityProductsBinding
import com.techtitans.ecommerce3.databinding.ActivityProfileBinding


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.quitBtn.setOnClickListener {
            val email = binding.MyEmail.toString()
            val ad=binding.MyAd.text.toString()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

}