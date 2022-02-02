package ru.a_party.ch3ch2l.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.a_party.ch3ch2l.R
import ru.a_party.ch3ch2l.model.data.DataModel

class TranslateAdapter: RecyclerView.Adapter<TranslateAdapter.TransalteHolder>() {

    var data: List<DataModel>?=null
    @SuppressLint("NotifyDataSetChanged")
    set(value){
        field=value
        notifyDataSetChanged()
    }

    class TransalteHolder(val itemView: View,
                          val tvWord:TextView = itemView.findViewById<TextView>(R.id.tvWord),
                          val tvTranslate:TextView = itemView.findViewById<TextView>(R.id.tvTranslate)
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: DataModel) {
            tvWord.text = data.text
            tvTranslate.text =
                data.meanings?.get(0)?.translation?.translation
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransalteHolder {
        return TransalteHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.translate_item, parent, false) as View,
        )
    }

    override fun onBindViewHolder(holder: TransalteHolder, position: Int) {
        data?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?:0
    }
}