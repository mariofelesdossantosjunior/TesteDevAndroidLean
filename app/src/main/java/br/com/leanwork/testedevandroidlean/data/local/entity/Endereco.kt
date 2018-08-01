package br.com.leanwork.testedevandroidlean.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "endereco")
data class Endereco(
        /**
         * private long idEnderecoCadastrado;
         * private final String cep, endereco, bairro, cidade, estado;
         */

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idEnderecoCadastrado")
        val idEnderecoCadastrado: Int? = null,

        @ColumnInfo(name = "cep")
        val cep: String,

        @ColumnInfo(name = "endereco")
        val endereco: String,

        @ColumnInfo(name = "bairro")
        val bairro: String,

        @ColumnInfo(name = "cidade")
        val cidade: String,

        @ColumnInfo(name = "estado")
        val estado: String
)
