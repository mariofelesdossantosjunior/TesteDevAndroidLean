package br.com.leanwork.testedevandroidlean.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import br.com.leanwork.testedevandroidlean.data.local.entity.Endereco
import br.com.leanwork.testedevandroidlean.data.local.entity.Pessoa

@Dao
interface EnderecoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEndereco(endereco: Endereco): Long

    @Update
    fun updateEndereco(endereco: Endereco)

    @Delete
    fun deleteEndereco(endereco: Endereco)

    @Query("SELECT * FROM endereco")
    fun loadEndereco(): LiveData<MutableList<Endereco>>

    @Query("SELECT * FROM endereco WHERE idEnderecoCadastrado = :id")
    fun getEnderecoById(id: Long): Endereco?

    @Query("DELETE FROM endereco")
    fun removeAll()
}