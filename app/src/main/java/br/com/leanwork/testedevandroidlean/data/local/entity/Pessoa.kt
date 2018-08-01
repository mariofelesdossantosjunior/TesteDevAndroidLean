package br.com.leanwork.testedevandroidlean.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "pessoa")
data class Pessoa(
        /**
         *private long idPessoaCadastrada;
         *private final String nome, genero, dataNascimento, telefone;
         */

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idPessoaCadastrada")
        val idPessoaCadastrada: Int? = null,

        @ColumnInfo(name = "nome")
        val nome: String,

        @ColumnInfo(name = "genero")
        val genero: String,

        @ColumnInfo(name = "dataNascimento")
        val dataNascimento: String,

        @ColumnInfo(name = "telefone")
        val telefone: String
)
