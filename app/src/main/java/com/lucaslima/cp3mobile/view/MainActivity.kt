package com.lucaslima.cp3mobile.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucaslima.cp3mobile.databinding.ActivityMainBinding
import com.lucaslima.cp3mobile.viewmodel.UsuariosViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var usuariosAdapter: UsuariosAdapter

    private val viewModel = UsuariosViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        sharedPrefs = getSharedPreferences("USUARIOS", Context.MODE_PRIVATE)
        viewModel.sharedPrefs = sharedPrefs
        setContentView(binding.root)

        viewModel.recuperarDadosNoApp()


        usuariosAdapter = UsuariosAdapter(viewModel.listaUsuarios)
        binding.recyclerViewUsuarios.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewUsuarios.adapter = usuariosAdapter

    }
}