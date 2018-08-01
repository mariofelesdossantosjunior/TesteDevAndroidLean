package br.com.leanwork.testedevandroidlean.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.com.leanwork.testedevandroidlean.data.local.entity.Pessoa

@Dao
interface PessoaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPessoa(pessoa: Pessoa): Long

    @Update
    fun updatePessoa(pessoa: Pessoa)

    @Delete
    fun deletePessoa(pessoa: Pessoa)

    @Query("SELECT * FROM pessoa")
    fun loadPessoa(): LiveData<MutableList<Pessoa>>

    @Query("SELECT * FROM pessoa WHERE idPessoaCadastrada = :id")
    fun getPessoaById(id: Long): Pessoa?

    @Query("DELETE FROM pessoa")
    fun removeAll()
}