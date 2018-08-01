package br.com.leanwork.testedevandroidlean.iu.detalhe

import android.arch.lifecycle.ViewModel
import br.com.leanwork.testedevandroidlean.data.repository.RepositoryEndereco
import br.com.leanwork.testedevandroidlean.data.repository.RepositoryPessoa

class DetalheViewModel(
        private val repositoryEndereco: RepositoryEndereco,
        private val repositoryPessoa: RepositoryPessoa)
    : ViewModel() {

    fun findEnderecoById(id: Long) = repositoryEndereco.findById(id)

    fun findPessoaById(id: Long) = repositoryPessoa.findById(id)

}
