package br.com.leanwork.testedevandroidlean.iu.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


abstract class BaseListAdapter<T>(private val callback: (T) -> Unit)
    : RecyclerView.Adapter<BaseListAdapter<T>.ViewHolder>() {

    protected var items = ArrayList<T>()

    abstract fun getListItemView(context: Context): BaseViewHolder<T>

    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addItems(itemsToAdd: List<T>) {
        items.addAll(itemsToAdd)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(getListItemView(parent.context))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]) {
            holder.view.bind(items[position])
            holder.view.setOnClickListener { callback(this) }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val view: BaseViewHolder<T>) : RecyclerView.ViewHolder(view)

}