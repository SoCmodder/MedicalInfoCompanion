package world.mitchmiller.medicalinfo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Appointment

class MedicalItemAdapter internal constructor(context: Context, itemClickListener: OnItemClickListener) : RecyclerView.Adapter<MedicalItemAdapter.MedItemViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var medicalItems = emptyList<Appointment>() // Cached copy of medical items
    private val listener: OnItemClickListener = itemClickListener

    companion object {
        const val NAME_SORT = 11
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedItemViewHolder {
        val itemView = inflater.inflate(R.layout.medical_list_item, parent, false)
        return MedItemViewHolder(itemView)
    }

    public interface OnItemClickListener {
        fun onItemClick(appointment: Appointment)
    }

    override fun getItemCount() = medicalItems.size

    override fun onBindViewHolder(holder: MedItemViewHolder, position: Int) {
        val current = medicalItems[position]
        holder.medItemView.text = current.name.capitalize()
    }

    internal fun setMedItems(appointments: List<Appointment>) {
        this.medicalItems = appointments
        notifyDataSetChanged()
    }

    internal fun sortMedicalItems(sortMethod: Int) {
        val result = medicalItems.sortedBy {
            when (sortMethod) {
                NAME_SORT -> it.name
                else -> it.name
            }
        }
        setMedItems(result)
    }

    inner class MedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medItemView: TextView = itemView.findViewById(R.id.name)
    }
}