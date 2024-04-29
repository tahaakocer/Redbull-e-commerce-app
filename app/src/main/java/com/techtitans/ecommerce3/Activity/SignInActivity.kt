package com.techtitans.ecommerce3.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techtitans.ecommerce3.databinding.ActivitiySignInBinding


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitiySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitiySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignin.setOnClickListener {
            val email = binding.etUserName.text.toString()
            val pass = binding.etPassword.text.toString()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}