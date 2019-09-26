package world.mitchmiller.medicalinfo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Appointment
import world.mitchmiller.medicalinfo.db.model.Doctor

class DoctorAdapter internal constructor(context: Context, itemClickListener: OnItemClickListener) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var doctors = emptyList<Doctor>() // Cached copy of medical items
    private val listener: OnItemClickListener = itemClickListener

    companion object {
        const val NAME_SORT = 11
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val itemView = inflater.inflate(R.layout.medical_list_item, parent, false)
        return DoctorViewHolder(itemView)
    }

    public interface OnItemClickListener {
        fun onItemClick(appointment: Appointment)
    }

    override fun getItemCount() = doctors.size

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val current = doctors[position]
        holder.medItemView.text = current.name.capitalize()
    }

    internal fun setMedItems(doctors: List<Doctor>) {
        this.doctors = doctors
        notifyDataSetChanged()
    }

    internal fun sortMedicalItems(sortMethod: Int) {
        val result = doctors.sortedBy {
            when (sortMethod) {
                NAME_SORT -> it.name
                else -> it.name
            }
        }
        setMedItems(result)
    }

    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medItemView: TextView = itemView.findViewById(R.id.name)
    }
}