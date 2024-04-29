package com.techtitans.ecommerce3.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techtitans.ecommerce3.API.LoginService
import com.techtitans.ecommerce3.databinding.ActivitySignUpBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val BASE_URL = "http://93.95.26.208:8080/api/"
    public var name =""
    public var email=""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(LoginService::class.java)

        binding.btnSignUp.setOnClickListener {
            val name = binding.etFullNameSignUp.text.toString()
            val email = binding.etEMailSignUp.text.toString()
            val pass = binding.etPasswordSignUp.text.toString()
            val confirmPass = binding.etPasswordSignUpTekrar.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    apiService.registerUser(name, email, pass).enqueue(object : Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@SignUpActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                                // Redirect to SignInActivity after successful registration
                                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this@SignUpActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Toast.makeText(this@SignUpActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })



                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}