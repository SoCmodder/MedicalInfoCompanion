package world.mitchmiller.medicalinfo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Document
import world.mitchmiller.medicalinfo.db.model.Appointment

class DocumentAdapter internal constructor(context: Context, itemClickListener: OnItemClickListener) : RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var docs = emptyList<Document>() // Cached copy of docs
    private val listener: OnItemClickListener = itemClickListener

    companion object {
        const val NAME_SORT = 11
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val itemView = inflater.inflate(R.layout.doc_list_item, parent, false)
        return DocumentViewHolder(itemView)
    }

    public interface OnItemClickListener {
        fun onItemClick(appointment: Appointment)
    }

    override fun getItemCount() = docs.size

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        val current = docs[position]
        holder.docView.text = current.name.capitalize()
    }

    internal fun setMedItems(docs: List<Document>) {
        this.docs = docs
        notifyDataSetChanged()
    }

    internal fun sortMedicalItems(sortMethod: Int) {
        val result = docs.sortedBy {
            when (sortMethod) {
                NAME_SORT -> it.name
                else -> it.name
            }
        }
        setMedItems(result)
    }

    inner class DocumentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val docView: TextView = itemView.findViewById(R.id.name)
    }
}