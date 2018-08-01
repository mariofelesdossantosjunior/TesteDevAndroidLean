package br.com.leanwork.testedevandroidlean.iu.pessoa

import android.content.Context
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.data.local.entity.Genero
import br.com.leanwork.testedevandroidlean.data.local.entity.Pessoa
import br.com.leanwork.testedevandroidlean.iu.adapter.BaseListAdapter
import br.com.leanwork.testedevandroidlean.iu.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_view_pessoa.view.*


class PessoaAdapter(callbacks: (Pessoa) -> Unit)
    : BaseListAdapter<Pessoa>(callbacks) {

    override fun getListItemView(context: Context): BaseViewHolder<Pessoa> = ViewHolder(context)

    inner class ViewHolder(context: Context?) : BaseViewHolder<Pessoa>(context) {
        override fun layoutResId(): Int = R.layout.item_view_pessoa

        override fun bind(item: Pessoa) {
            tvNomePessoa.text = item.nome
            tvTelefonePessoa.text = item.telefone
            tvDataNascimentoPessoa.text = item.dataNascimento
            tvGeneroPessoa.text = item.genero

            //Muda o icone conforme o genero
            if (item.genero == Genero.MASCULINO.name) {
                tvGeneroPessoa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gender_male, 0, 0, 0)
            } else {
                tvGeneroPessoa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gender_female, 0, 0, 0)
            }
        }
    }
}
