package com.techtitans.ecommerce3.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techtitans.ecommerce3.API.BannerService
import com.techtitans.ecommerce3.API.CategoryService
import com.techtitans.ecommerce3.API.ProductService
import com.techtitans.ecommerce3.Client.RetrofitClient

import com.techtitans.ecommerce3.Model.Banner
import com.techtitans.ecommerce3.Model.Category
import com.techtitans.ecommerce3.Model.CategoryModel
import com.techtitans.ecommerce3.Model.ItemsModel
import com.techtitans.ecommerce3.Model.Product
import com.techtitans.ecommerce3.Model.SliderModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainViewModel {

    private val BASE_URL = "http://93.95.26.208:8080/api/"

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _category = MutableLiveData<List<CategoryModel>>()
    private val _bestSeller = MutableLiveData<MutableList<ItemsModel>>()


    val banners: LiveData<List<SliderModel>> = _banner
    val category: LiveData<List<CategoryModel>> = _category
    val bestSeller: LiveData<MutableList<ItemsModel>> = _bestSeller


    val retrofit = Retrofit.Builder()
        .baseUrl(RetrofitClient.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // UserService üzerinden API istemcisi oluşturma
    val bannerService = retrofit.create(BannerService::class.java)
    val categoryService = retrofit.create(CategoryService::class.java)
    val productService = retrofit.create(ProductService::class.java)


    fun loadBanners()  {
        val sliderList = mutableListOf<SliderModel>()
        val call = bannerService.getBanners()
        call.enqueue(object : Callback<List<Banner>> {
            override fun onResponse(call: Call<List<Banner>>, response: Response<List<Banner>>) {
                if (response.isSuccessful) {
                    val bannerList = response.body()
                    bannerList?.let {

                        for (banner in bannerList) {
                           var sliderModel = SliderModel(banner.url)
                            sliderList.add(sliderModel)
                        }
                        _banner.value = sliderList
                    }
                } else {
                    println("Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Banner>>, t: Throwable) {
                println("Network request failed: ${t.message}")
            }
        })
    }

    fun loadCategory() {
        val categoryModelList = mutableListOf<CategoryModel>()
        val call = categoryService.getCategories()
        call.enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    val categoryList = response.body()
                    categoryList?.let {
                        for (category in categoryList) {
                            // Burada CategoryModel oluştururken title parametresini null olmayacak şekilde sağlıyoruz
                            val categoryModel = CategoryModel(category.id, category.title ?: "", category.picUrl ?: "")
                            categoryModelList.add(categoryModel)
                        }
                        _category.value = categoryModelList
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
    fun loadBestSeller(){
        val itemsModelList = mutableListOf<ItemsModel>()
        val call = productService.getProducts()
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    val productList = response.body()
                    productList?.let {
                        for (product in productList) {
                            val itemModel = ItemsModel(product.title,product.description,product.thumbnail,product.price,product.rating,0,"RedBull",product.sellerTell,product.stock)
                            itemsModelList.add(itemModel)
                        }
                        _bestSeller.value = itemsModelList
                    }
                } else {
                    println("Error: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                println("Network request failed: ${t.message}")
            }
        })


    }

}