package br.com.leanwork.testedevandroidlean.iu.endereco

import android.arch.lifecycle.ViewModel
import br.com.leanwork.testedevandroidlean.data.local.entity.Endereco
import br.com.leanwork.testedevandroidlean.data.repository.RepositoryEndereco

class EnderecoViewModel(
        private val repositoryEndereco: RepositoryEndereco)
    : ViewModel() {

    fun getAllEnderecos() = repositoryEndereco.findAll()

    fun saveEndereco(endereco: Endereco) = repositoryEndereco.save(endereco)

    fun findEnderecoByCep(cep: String) = repositoryEndereco.findByCep(cep)

}
