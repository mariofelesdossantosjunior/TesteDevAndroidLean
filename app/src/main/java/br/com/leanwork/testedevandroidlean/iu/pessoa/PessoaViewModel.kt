package br.com.leanwork.testedevandroidlean.iu.pessoa

import android.arch.lifecycle.ViewModel
import br.com.leanwork.testedevandroidlean.data.local.entity.Pessoa
import br.com.leanwork.testedevandroidlean.data.repository.RepositoryPessoa

class PessoaViewModel(
        private val repositoryPessoa: RepositoryPessoa)
    : ViewModel() {

    fun getAllPessoas() = repositoryPessoa.findAll()

    fun savePessoa(pessoa: Pessoa) = repositoryPessoa.save(pessoa)

}
