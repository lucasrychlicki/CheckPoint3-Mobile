package com.lucaslima.cp3mobile.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.lucaslima.cp3mobile.model.Usuario

class UsuariosViewModel() : ViewModel() {

    private val gson = GsonBuilder().create()
    var sharedPrefs: SharedPreferences? = null
    val listaUsuarios = arrayListOf<Usuario>()

    fun salvarDadosNoApp(usuarios: MutableList<Usuario>) {
        val jsonString = gson.toJson(usuarios)
        sharedPrefs?.edit()?.putString("USUARIOS", jsonString)?.apply()
    }

    fun recuperarDadosNoApp() {
        val jsonString = sharedPrefs?.getString("USUARIOS", "[]")
        val lista = gson.fromJson(jsonString, Array<Usuario>::class.java)

        listaUsuarios.addAll(lista)
    }

    fun verificarLogin(email: String, senha: String): Boolean {
        return listaUsuarios.any { it.email == email && it.senha == senha }
    }
}