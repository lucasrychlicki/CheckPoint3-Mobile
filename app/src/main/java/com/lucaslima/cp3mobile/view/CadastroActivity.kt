package com.lucaslima.cp3mobile.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.lucaslima.cp3mobile.databinding.ActivityCadastroBinding
import com.lucaslima.cp3mobile.model.Usuario
import com.lucaslima.cp3mobile.viewmodel.UsuariosViewModel

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var sharedPrefs: SharedPreferences
    private val gson = GsonBuilder().create()

    val viewModel = UsuariosViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        sharedPrefs = getSharedPreferences("USUARIOS", Context.MODE_PRIVATE)
        viewModel.sharedPrefs = sharedPrefs
        setContentView(binding.root)

        viewModel.recuperarDadosNoApp()

        binding.btnCadastrar.setOnClickListener {
            cadastrarUsuario()
        }

    }

    private fun cadastrarUsuario() {
        val email = binding.editEmailCadastro.text.toString()
        val nome = binding.editNomeCadastro.text.toString()
        val idade = binding.editIdadeCadastro.text.toString()
        val endereco = binding.editEnderecoCadastro.text.toString()
        val telefone = binding.editTelefoneCadastro.text.toString()
        val nacionalidade = binding.editNacionalidadeCadastro.text.toString()
        val ocupacao = binding.editOcupacaoCadastro.text.toString()
        val hobby = binding.editHobbyCadastro.text.toString()
        val senha = binding.editSenhaCadastro.text.toString()
        val confirmaSenha = binding.editConfirmaSenhaCadastro.text.toString()

        val errorMsg = validarCampos(email, nome, idade, endereco, telefone, nacionalidade, ocupacao, hobby, senha, confirmaSenha)
        if (errorMsg != null) {
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            return
        }

        val novoUsuario = Usuario(
            nome = nome,
            idade = idade,
            endereco = endereco,
            telefone = telefone,
            nacionalidade = nacionalidade,
            ocupacao = ocupacao,
            hobby = hobby,
            email = email,
            senha = senha
        )

        viewModel.listaUsuarios.add(novoUsuario)
        viewModel.salvarDadosNoApp(viewModel.listaUsuarios)

        val intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun validarCampos(
        email: String, nome: String, idade: String, endereco: String, telefone: String,
        nacionalidade: String, ocupacao: String, hobby: String, senha: String, confirmaSenha: String
    ): String? {
        return when {
            email.isEmpty() -> "Preencha o campo email!"
            nome.isEmpty() -> "Preencha o campo nome!"
            idade.isEmpty() -> "Preencha o campo idade!"
            endereco.isEmpty() -> "Preencha o campo endereço!"
            telefone.isEmpty() -> "Preencha o campo telefone!"
            nacionalidade.isEmpty() -> "Preencha o campo nacionalidade!"
            ocupacao.isEmpty() -> "Preencha o campo ocupação!"
            hobby.isEmpty() -> "Preencha o campo hobby!"
            senha.isEmpty() -> "Digite a senha!"
            confirmaSenha.isEmpty() -> "Confirme a sua senha!"
            senha != confirmaSenha -> "Senhas diferentes!"
            else -> null
        }
    }
}


