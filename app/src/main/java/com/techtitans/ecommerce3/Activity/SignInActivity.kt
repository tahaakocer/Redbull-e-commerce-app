package com.techtitans.ecommerce3.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techtitans.ecommerce3.API.LoginService
import com.techtitans.ecommerce3.Client.userConfig
import com.techtitans.ecommerce3.Model.LoginResponse
import com.techtitans.ecommerce3.databinding.ActivitiySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitiySignInBinding
    private val BASE_URL = "http://93.95.26.208:8080/api/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitiySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Initialize apiService
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(LoginService::class.java)

        binding.btnSignin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                apiService.loginUser(email, password).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse != null && loginResponse.status) {

                                Toast.makeText(this@SignInActivity, loginResponse.message, Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this@SignInActivity, "Giriş Başarısız", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@SignInActivity, "Giriş Başarısız", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@SignInActivity, "Hata: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this@SignInActivity, "Tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
            }
        }
    }

}