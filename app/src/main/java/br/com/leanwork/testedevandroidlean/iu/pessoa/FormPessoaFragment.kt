package br.com.leanwork.testedevandroidlean.iu.pessoa

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.data.local.entity.Genero
import br.com.leanwork.testedevandroidlean.data.local.entity.Pessoa
import br.com.leanwork.testedevandroidlean.util.FormUtil
import br.com.leanwork.testedevandroidlean.util.PushCadastro
import br.com.leanwork.testedevandroidlean.util.ServiceUtil
import kotlinx.android.synthetic.main.fragment_form_pessoa.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.architecture.ext.viewModel
import java.util.*

class FormPessoaFragment : Fragment() {

    companion object {
        fun newInstance() = FormPessoaFragment()
    }

    private val viewModel by viewModel<PessoaViewModel>()

    private lateinit var picker: DatePickerDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_form_pessoa, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        spnGenero.adapter = ArrayAdapter<Genero>(context, android.R.layout.simple_spinner_item, Genero.values())

        etDataNascimento.setOnClickListener {
            openDialogDatePicker()
        }

        btFinalizarCadastroPessoa.setOnClickListener {
            if (formValido()) {

                cadastroRealizadoComSucesso(
                        viewModel.savePessoa(
                                Pessoa(
                                        nome = etNomeCompleto.text.toString(),
                                        telefone = etTelefoneCelular.text.toString(),
                                        genero = spnGenero.selectedItem.toString(),
                                        dataNascimento = etDataNascimento.text.toString()

                                )
                        )
                )
            } else {
                toast("Preencha o formulário corretamente!")
            }
        }

        btLimparCadastroPessoa.setOnClickListener { limparCadastro() }
    }

    @SuppressLint("SetTextI18n")
    private fun openDialogDatePicker() {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            etDataNascimento.setText("$dayOfMonth/$monthOfYear/$year")
        }, year, month, day)

        dpd.show()
    }

    private fun cadastroRealizadoComSucesso(idPessoa: Long) {
        limparCadastro()
        PushCadastro.disparar(context, "Pessoa", idPessoa)
        ServiceUtil.esconderTeclado(context, etNomeCompleto)
        toast("Cadastro realizado com sucesso!")
    }

    private fun formValido() =
            FormUtil.formularioPreenchido(
                    if (etNomeCompleto.text != null) etNomeCompleto.text.toString() else "",
                    if (etTelefoneCelular.text != null) etTelefoneCelular.text.toString() else null,
                    spnGenero.selectedItem.toString(),
                    etDataNascimento.text.toString())

    private fun limparCadastro() {
        spnGenero.setSelection(0)
        etNomeCompleto.text = null
        etTelefoneCelular.text = null
        etDataNascimento.text = null
    }

}

