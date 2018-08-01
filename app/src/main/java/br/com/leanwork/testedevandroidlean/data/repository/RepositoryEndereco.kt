package br.com.leanwork.testedevandroidlean.data.repository

import br.com.leanwork.testedevandroidlean.data.local.dao.EnderecoDao
import br.com.leanwork.testedevandroidlean.data.local.entity.Endereco
import br.com.leanwork.testedevandroidlean.data.remote.ApiCep

class RepositoryEndereco(
        private val enderecoDao: EnderecoDao,
        private val apiCep: ApiCep)
    : IRepository<Endereco> {

    /**
     * Funcao responsavel por buscar endereco pelo cep na API
     */
    fun findByCep(cep: String) = apiCep.findEnderecoByCep(cep)

    /**
     * Funcao responsavel por buscar endereco pelo codigo
     */
    fun findById(id: Long) = enderecoDao.getEnderecoById(id)

    /**
     * Funcao responsavel por buscar todas as enderecos no database
     */
    override fun findAll() = enderecoDao.loadEndereco()

    /**
     * Funcao responsavel por salvar a endereco no database
     */
    override fun save(obj: Endereco) = enderecoDao.insertEndereco(obj)

    /**
     * Funcao responsavel por remover a endereco no database
     */
    override fun delete(obj: Endereco) = enderecoDao.deleteEndereco(obj)

}