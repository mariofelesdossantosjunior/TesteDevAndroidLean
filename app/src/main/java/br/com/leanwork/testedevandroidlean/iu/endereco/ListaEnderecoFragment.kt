package br.com.leanwork.testedevandroidlean.iu.endereco

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.data.local.entity.Endereco
import kotlinx.android.synthetic.main.fragment_lista_enderecos.*
import org.koin.android.architecture.ext.viewModel

class ListaEnderecoFragment : Fragment() {

    companion object {
        fun newInstance() = ListaEnderecoFragment()
    }

    private val viewModel by viewModel<EnderecoViewModel>()

    private val adapter: EnderecoAdapter by lazy {
        EnderecoAdapter { it }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_lista_enderecos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycle()
        viewModel.getAllEnderecos().observe(this, Observer { it?.let { showEndereco(it) } })
    }

    private fun showEndereco(itens: List<Endereco>) {
        adapter.clearItem()
        adapter.addItems(itens)
    }

    private fun setupRecycle() {
        rvListaEnderecos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvListaEnderecos.setHasFixedSize(true)
        rvListaEnderecos.adapter = adapter
    }
}
