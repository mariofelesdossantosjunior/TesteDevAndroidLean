package br.com.leanwork.testedevandroidlean.iu.endereco

import android.content.Context
import br.com.leanwork.testedevandroidlean.R
import br.com.leanwork.testedevandroidlean.data.local.entity.Endereco
import br.com.leanwork.testedevandroidlean.iu.adapter.BaseListAdapter
import br.com.leanwork.testedevandroidlean.iu.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_view_endereco_modelo_2.view.*
import java.util.*


class EnderecoAdapter(callbacks: (Endereco) -> Unit)
    : BaseListAdapter<Endereco>(callbacks) {

    override fun getListItemView(context: Context): BaseViewHolder<Endereco> {
        return if (Random().nextInt() % 2 == 0) {
            ViewHolder01(context)
        } else {
            ViewHolder02(context)
        }
    }

    inner class ViewHolder01(context: Context?) : BaseViewHolder<Endereco>(context) {
        override fun layoutResId(): Int = R.layout.item_view_endereco_modelo_1

        override fun bind(item: Endereco) {
            tvCidade.text = item.cidade
            tvCep.text = item.cep
            tvBairro.text = item.bairro
            tvEndereco.text = item.endereco
            tvEstado.text = item.estado
        }
    }

    inner class ViewHolder02(context: Context?) : BaseViewHolder<Endereco>(context) {
        override fun layoutResId(): Int = R.layout.item_view_endereco_modelo_2

        override fun bind(item: Endereco) {
            tvCidade.text = item.cidade
            tvCep.text = item.cep
            tvBairro.text = item.bairro
            tvEndereco.text = item.endereco
            tvEstado.text = item.estado
        }
    }
}
