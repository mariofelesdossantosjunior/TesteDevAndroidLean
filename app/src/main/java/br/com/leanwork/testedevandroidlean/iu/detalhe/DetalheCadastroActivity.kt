package br.com.leanwork.testedevandroidlean.iu.detalhe

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.data.local.entity.Genero
import br.com.leanwork.testedevandroidlean.iu.menu.MainActivity
import kotlinx.android.synthetic.main.activity_detalhe_cadastro.*
import kotlinx.android.synthetic.main.item_view_endereco_detalhe.*
import kotlinx.android.synthetic.main.item_view_pessoa_detalhe.*
import org.koin.android.architecture.ext.viewModel

class DetalheCadastroActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetalheViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_cadastro)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras

        if (extras != null) {
            val tipoCadastro = extras.getString(ExtraTipoCadastro)
            val idCadastro = extras.getLong(ExtraIdCadastro)

            (findViewById<View>(R.id.tvMensagem) as TextView).text = "Novo Cadastro de $tipoCadastro!\nBora Exibir os detalhes?"

            if (tipoCadastro != null && tipoCadastro.equals("Pessoa", ignoreCase = true)) {
                view_pessoa.visibility = View.VISIBLE
                view_endereco.visibility = View.GONE
                bindValuesPessoa(idCadastro)
            } else {
                view_pessoa.visibility = View.GONE
                view_endereco.visibility = View.VISIBLE
                bindValuesEndereco(idCadastro)
            }
        }
    }

    private fun bindValuesPessoa(idCadastro: Long) {
        viewModel.findPessoaById(idCadastro)?.let {
            tvNomePessoa.text = it.nome
            tvTelefonePessoa.text = it.telefone
            tvDataNascimentoPessoa.text = it.dataNascimento
            tvGeneroPessoa.text = it.genero

            //Muda o icone conforme o genero
            if (it.genero == Genero.MASCULINO.name) {
                tvGeneroPessoa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gender_male, 0, 0, 0)
            } else {
                tvGeneroPessoa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gender_female, 0, 0, 0)
            }
        }


    }

    private fun bindValuesEndereco(idCadastro: Long) {
        viewModel.findEnderecoById(idCadastro)?.let {
            tvCidade.text = it.cidade
            tvCep.text = it.cep
            tvBairro.text = it.bairro
            tvEndereco.text = it.endereco
            tvEstado.text = it.estado
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var ExtraTipoCadastro = "TipoCadastro"
        var ExtraIdCadastro = "IDCadastro"
    }
}
