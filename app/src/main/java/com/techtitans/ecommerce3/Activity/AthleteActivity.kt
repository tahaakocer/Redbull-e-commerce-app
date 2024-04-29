package com.techtitans.ecommerce3.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.techtitans.ecommerce3.R
import com.techtitans.ecommerce3.ViewModel.MainViewModel
import com.techtitans.ecommerce3.databinding.ActivityAthleteBinding
import com.techtitans.ecommerce3.databinding.ActivityIntroBinding
import com.techtitans.ecommerce3.databinding.ActivityProductsBinding

class AthleteActivity : BaseActivity() {

    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityAthleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityAthleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomNavigation()
    }

    private fun bottomNavigation() {
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@AthleteActivity,CartActivity::class.java))
        }
        binding.productsBtn.setOnClickListener{
            startActivity(Intent(this@AthleteActivity,ProductsActivity::class.java))

        }
        binding.explorerBtn.setOnClickListener{
            startActivity(Intent(this@AthleteActivity,MainActivity::class.java))

        }

    }
}