package com.lucaslima.cp3mobile.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lucaslima.cp3mobile.R
import com.lucaslima.cp3mobile.databinding.ActivityLoginBinding
import com.lucaslima.cp3mobile.viewmodel.UsuariosViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPrefs: SharedPreferences

    private val viewModel = UsuariosViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        sharedPrefs = getSharedPreferences("USUARIOS", Context.MODE_PRIVATE)
        viewModel.sharedPrefs = sharedPrefs
        setContentView(binding.root)

        viewModel.recuperarDadosNoApp()

        binding.btnLogin.setOnClickListener {
            realizarLogin()
        }

    }

    private fun realizarLogin() {
        val email = binding.editEmailLogin.text.toString()
        val senha = binding.editSenhaLogin.text.toString()

        if (email.isNotEmpty() && senha.isNotEmpty()) {
            val loginValido = viewModel.verificarLogin(email, senha)
            if (loginValido) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show()
        }
    }

}