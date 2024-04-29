package com.techtitans.ecommerce3.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techtitans.ecommerce3.API.AtletService
import com.techtitans.ecommerce3.API.BannerService
import com.techtitans.ecommerce3.API.UserService
import com.techtitans.ecommerce3.Client.RetrofitClient
import com.techtitans.ecommerce3.Client.userConfig
import com.techtitans.ecommerce3.Model.User
import com.techtitans.ecommerce3.Model.UserResponse
import com.techtitans.ecommerce3.databinding.ActivityProfileBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val api = RetrofitClient.getUserApi()
            val call = api.getUserByEmail(userConfig.email)
            call.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        binding.MyAd.text = user?.name?: ""
                        binding.MyEmail.text = user?.email?: ""
                    } else {
                        // Handle error
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    // Handle error
                }
            })



        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.quitBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showError(message: String) {
        // Show error message to the user
        // You can use a Toast or a Snackbar to show the error
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}