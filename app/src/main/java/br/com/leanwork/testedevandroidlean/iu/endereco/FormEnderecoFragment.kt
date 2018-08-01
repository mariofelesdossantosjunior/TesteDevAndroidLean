package br.com.leanwork.testedevandroidlean.iu.endereco

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.data.local.entity.Endereco
import br.com.leanwork.testedevandroidlean.data.remote.entity.CepResponse
import br.com.leanwork.testedevandroidlean.util.FormUtil
import br.com.leanwork.testedevandroidlean.util.PushCadastro
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_form_endereco.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.architecture.ext.viewModel


class FormEnderecoFragment : Fragment() {

    companion object {
        fun newInstance() = FormEnderecoFragment()
    }

    private val viewModel by viewModel<EnderecoViewModel>()

    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_form_endereco, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        etCep.setOnFocusChangeListener { _, focus ->
            if (!focus) {
                disposable.add(
                        viewModel.findEnderecoByCep(etCep.text.toString())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        { preencherCep(it) },
                                        { toast("Não foi possivel buscar o CEP") })
                )
            }
        }

        btFinalizarCadastroEndereco.setOnClickListener {
            if (formValido()) {

                cadastroRealizadoComSucesso(
                        viewModel.saveEndereco(
                                Endereco(cep = etCep.text.toString(),
                                        cidade = etCidade.text.toString(),
                                        estado = etEstado.text.toString(),
                                        bairro = etBairro.text.toString(),
                                        endereco = etLogradouro.text.toString())
                        )
                )
            } else {
                toast("Preencha o formulário corretamente!")
            }
        }

        btLimparCadastroEndereco.setOnClickListener { limparCadastro() }
    }

    private fun cadastroRealizadoComSucesso(idEndereco: Long) {
        limparCadastro()
        PushCadastro.disparar(context, "Endereço", idEndereco)
        toast("Cadastro realizado com sucesso!")
    }

    private fun formValido() =
            FormUtil.formularioPreenchido(
                    if (etCep.text != null) etCep.text.toString() else "",
                    if (etLogradouro.text != null) etLogradouro.text.toString() else null,
                    if (etBairro.text != null) etBairro.text.toString() else null,
                    if (etCidade.text != null) etCidade.text.toString() else null,
                    if (etEstado.text != null) etEstado.text.toString() else null)

    private fun limparCadastro() {
        etCep.text = null
        etCidade.text = null
        etBairro.text = null
        etEstado.text = null
        etLogradouro.text = null
    }

    private fun preencherCep(cepResponse: CepResponse) {
        etCep.setText(cepResponse.cep)
        etLogradouro.setText(cepResponse.logradouro)
        etBairro.setText(cepResponse.bairro)
        etCidade.setText(cepResponse.localidade)
        etEstado.setText(cepResponse.uf)
    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}

