package br.com.leanwork.testedevandroidlean.data.repository

import br.com.leanwork.testedevandroidlean.data.local.dao.PessoaDao
import br.com.leanwork.testedevandroidlean.data.local.entity.Pessoa

class RepositoryPessoa(
        private val pessoaDao: PessoaDao)
    : IRepository<Pessoa> {

    /**
     * Funcao responsavel por buscar pessoa pelo codigo
     */
    fun findById(id: Long) = pessoaDao.getPessoaById(id)

    /**
     * Funcao responsavel por buscar todas as pessoas no database
     */
    override fun findAll() = pessoaDao.loadPessoa()

    /**
     * Funcao responsavel por salvar a pessoa no database
     */
    override fun save(obj: Pessoa) = pessoaDao.insertPessoa(obj)

    /**
     * Funcao responsavel por remover a pessoa no database
     */
    override fun delete(obj: Pessoa) = pessoaDao.deletePessoa(obj)

}