package com.techtitans.ecommerce3.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.techtitans.ecommerce3.API.CategoryService
import com.techtitans.ecommerce3.Adapter.BestSellerAdapter
import com.techtitans.ecommerce3.Adapter.CategoryAdapter
import com.techtitans.ecommerce3.Adapter.SliderAdapter
import com.techtitans.ecommerce3.Model.Category
import com.techtitans.ecommerce3.Model.SliderModel
import com.techtitans.ecommerce3.ViewModel.MainViewModel
import com.techtitans.ecommerce3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanners()
        initCategories()
        initBestSeller()
        bottomNavigation()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://93.95.26.208:8080/api/") // API'nin temel URL'si
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // UserService üzerinden API istemcisi oluşturma
        val productService = retrofit.create(CategoryService::class.java)

        val call = productService.getCategories()
        call.enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    val productList = response.body()
                    productList?.let {
                        // Ürün listesini yazdırma
                        println("Product List:")
                        for (product in productList) {
                            println("ID: ${product.id}")
                            println("Name: ${product.picUrl}")
                            println("Price: ${product.title}")
                            println("------------")
                        }
                    }
                } else {
                    println("Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                println("Network request failed: ${t.message}")
            }
        })

    }

    private fun bottomNavigation() {
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,CartActivity::class.java))
        }
        binding.productsBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,ProductsActivity::class.java))

        }
        binding.seeAllBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,ProductsActivity::class.java))

        }
        binding.explorerBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,ProductsActivity::class.java))

        }
        binding.AthleteBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,AthleteActivity::class.java))

        }

    }

    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility=View.VISIBLE
        viewModel.bestSeller.observe(this, Observer {
            binding.viewBestSeller.layoutManager=GridLayoutManager(this,2)
            binding.viewBestSeller.adapter=BestSellerAdapter(it)
            binding.progressBarBestSeller.visibility=View.GONE
        })
        viewModel.loadBestSeller()
    }

    private fun initCategories() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.viewCategory.layoutManager =
                LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }

    private fun initBanners() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, androidx.lifecycle.Observer {
            banners(it)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanners()
    }

    private fun banners(images: List<SliderModel>) {
        binding.viewPagerSlider.adapter = SliderAdapter(images, binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit = 3
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)

    }
}