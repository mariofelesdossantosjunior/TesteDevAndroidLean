package br.com.leanwork.testedevandroidlean.data.remote

import br.com.leanwork.testedevandroidlean.data.remote.entity.CepResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCep {

    @GET("{cep}/json")
    fun findEnderecoByCep(@Path("cep") cep: String): Flowable<CepResponse>

}
