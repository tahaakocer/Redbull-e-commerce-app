package com.techtitans.ecommerce3.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.techtitans.ecommerce3.Adapter.AthleteApadter
import com.techtitans.ecommerce3.Adapter.BestSellerAdapter
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
        initAthlete()
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
    private fun initAthlete() {
        binding.progressBarBestSeller.visibility= View.VISIBLE
        viewModel.atlets.observe(this, Observer {
            binding.viewBestSeller.layoutManager= GridLayoutManager(this,2)
            binding.viewBestSeller.adapter= AthleteApadter(it)
            binding.progressBarBestSeller.visibility= View.GONE
        })
        viewModel.loadAtlets()
    }

}