package br.com.leanwork.testedevandroidlean.di

import android.arch.persistence.room.Room
import br.com.leanwork.testedevandroidlean.data.local.DataBase
import br.com.leanwork.testedevandroidlean.data.remote.ApiCep
import br.com.leanwork.testedevandroidlean.data.repository.RepositoryEndereco
import br.com.leanwork.testedevandroidlean.data.repository.RepositoryPessoa
import br.com.leanwork.testedevandroidlean.iu.detalhe.DetalheViewModel
import br.com.leanwork.testedevandroidlean.iu.endereco.EnderecoViewModel
import br.com.leanwork.testedevandroidlean.iu.pessoa.PessoaViewModel
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Koin main module
 */
val module = applicationContext {

    bean {
        Room.databaseBuilder(androidApplication(),
                DataBase::class.java,
                "lean-db")
                .allowMainThreadQueries()
                .build()
    }


    bean {
        Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")//Base Url Default
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                        .create()))
                .build()
    }

    bean { get<Retrofit>().create(ApiCep::class.java) }

    bean { get<DataBase>().pessoaDao() }
    bean { get<DataBase>().enderecoDao() }

    viewModel { EnderecoViewModel(get()) }
    viewModel { PessoaViewModel(get()) }
    viewModel { DetalheViewModel(get(), get()) }

    bean { RepositoryPessoa(get()) }
    bean { RepositoryEndereco(get(), get()) }

}

/**
 * module list
 */
val appModules = listOf(module)
