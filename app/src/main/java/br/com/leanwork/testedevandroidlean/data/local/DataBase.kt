package br.com.leanwork.testedevandroidlean.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import br.com.leanwork.testedevandroidlean.data.local.converter.ConverterDate
import br.com.leanwork.testedevandroidlean.data.local.dao.EnderecoDao
import br.com.leanwork.testedevandroidlean.data.local.dao.PessoaDao
import br.com.leanwork.testedevandroidlean.data.local.entity.Endereco
import br.com.leanwork.testedevandroidlean.data.local.entity.Pessoa

@Database(entities = [
    Endereco::class,
    Pessoa::class
], version = 1, exportSchema = false)

@TypeConverters(ConverterDate::class)

abstract class DataBase : RoomDatabase() {

    abstract fun pessoaDao(): PessoaDao

    abstract fun enderecoDao(): EnderecoDao
}