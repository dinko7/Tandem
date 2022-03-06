package com.demo.tandem.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<VB : ViewDataBinding, T>(private var layout: Int) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter<VB, T>.ViewHolder>() {

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root) {
        init {
            setClickableView(binding).forEach { clickView ->
                clickView?.setOnClickListener { view ->
                    notifyItemChanged(adapterPosition)
                    if (adapterPosition >= 0 && adapterPosition < displayList.size) {
                        onClickView?.let { it(view, adapterPosition, displayList[adapterPosition]) }
                    }
                }
            }
            setLongClickableView(binding).forEach { longClickView ->
                longClickView?.setOnLongClickListener { view ->
                    notifyItemChanged(adapterPosition)
                    if (adapterPosition >= 0 && adapterPosition < displayList.size) {
                        onLongClickView?.let {
                            it(view, adapterPosition, displayList[adapterPosition])
                        }
                    }
                    true
                }
            }
        }
    }

    protected val displayList = ArrayList<T>()

    private lateinit var mRecyclerView: RecyclerView

    private var onClickView: ((View, Int, T) -> Unit)? = null
    private var onLongClickView: ((View, Int, T) -> Unit)? = null
    private var onCreateViewHolderBlock: ((View) -> View)? = null

    open fun setClickableView(binding: VB): List<View?> = listOf(binding.root)
    abstract fun onBind(binding: VB, position: Int, item: T, vararg payloads: MutableList<Any>)

    open fun setLongClickableView(binding: VB): List<View?> = listOf()
    open fun onViewHolderBind(viewHolder: ViewHolder) {}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(holder.binding, position, displayList[position])
        onViewHolderBind(holder)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return displayList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            onBind(holder.binding, position, displayList[position], payloads)
            onViewHolderBind(holder)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    fun setItemClickListener(onClickView: (View, Int, T) -> Unit) {
        this.onClickView = onClickView
    }

    fun setItemLongClickListener(onLongClickView: (View, Int, T) -> Unit) {
        this.onLongClickView = onLongClickView
    }

    fun setOnCreateViewHolderBlock(block: ((View) -> View)) {
        this.onCreateViewHolderBlock = block
    }

    fun getItem(position: Int): T {
        return displayList[position]
    }

    fun getAllItems() = displayList

    fun addItemAt(index: Int, item: T) {
        displayList.add(index, item)
        notifyItemInserted(index)
    }

    fun addItem(item: T) {
        displayList.add(item)
        notifyItemInserted(displayList.size)
    }

    fun appendAll(dataList: Collection<T>) {
        displayList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addAll(dataList: Collection<T>) {
        displayList.clear()
        displayList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun removeItemAt(position: Int) {
        if (0 <= position && position < displayList.size) {
            displayList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun replaceItemAt(item: T, position: Int) {
        if (0 <= position && position < displayList.size) {
            displayList[position] = item
            notifyItemChanged(position)
        }
    }

    fun removeItem(item: T) {
        displayList.remove(item)
        notifyDataSetChanged()
    }

    fun clearDataSet() {
        val previousSize = displayList.size
        displayList.clear()
        notifyItemRangeRemoved(0, previousSize)
    }
}
