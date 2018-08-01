package br.com.leanwork.testedevandroidlean.iu.pessoa

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.data.local.entity.Pessoa
import kotlinx.android.synthetic.main.fragment_lista_pessoas.*
import org.koin.android.architecture.ext.viewModel

class ListaPessoasFragment : Fragment() {

    companion object {
        fun newInstance() = ListaPessoasFragment()
    }

    private val viewModel by viewModel<PessoaViewModel>()

    private val adapter: PessoaAdapter by lazy {
        PessoaAdapter { it }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_lista_pessoas, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycle()
        viewModel.getAllPessoas().observe(this, Observer { it?.let { showPessoas(it) } })
    }

    private fun showPessoas(itens: List<Pessoa>) {
        adapter.clearItem()
        adapter.addItems(itens)
    }

    private fun setupRecycle() {
        rvListaPessoas.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvListaPessoas.setHasFixedSize(true)
        rvListaPessoas.adapter = adapter
    }
}
