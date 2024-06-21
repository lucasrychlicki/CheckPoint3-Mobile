package com.lucaslima.cp3mobile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucaslima.cp3mobile.databinding.ItemUsuarioBinding
import com.lucaslima.cp3mobile.model.Usuario

class UsuariosAdapter(private val usuarios: List<Usuario>) : RecyclerView.Adapter<UsuariosAdapter.UsuarioViewHolder>() {

    inner class UsuarioViewHolder(val binding: ItemUsuarioBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val binding = ItemUsuarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsuarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        with(holder.binding) {
            textViewIdade.text = usuario.idade
            textViewNacionalide.text = usuario.nacionalidade
            textViewOcupacao.text = usuario.ocupacao
            textViewHobby.text = usuario.hobby
            textViewNome.text = usuario.nome
            textViewEmail.text = usuario.email
            textViewTelefone.text = usuario.telefone
            textViewEndereco.text = usuario.endereco

            // Mostra a senha com asteriscos por padrão
            textViewSenha.text = "*".repeat(usuario.senha.length)
            var senhaVisivel = false

            btnToggleSenha.setOnClickListener {
                senhaVisivel = !senhaVisivel
                textViewSenha.text = if (senhaVisivel) usuario.senha else "*".repeat(usuario.senha.length)
            }
        }
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }
}


