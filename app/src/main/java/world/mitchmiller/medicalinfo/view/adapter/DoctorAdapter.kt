package world.mitchmiller.medicalinfo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Doctor

class DoctorAdapter internal constructor(context: Context, itemClickListener: OnItemClickListener) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var doctors = emptyList<Doctor>() // Cached copy of medical items
    private val listener: OnItemClickListener = itemClickListener

    companion object {
        const val NAME_SORT = 11
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val itemView = inflater.inflate(R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(itemView)
    }

    interface OnItemClickListener {
        fun onItemClick(doctor: Doctor)
    }

    override fun getItemCount() = doctors.size

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val current = doctors[position]
        holder.doctorName.text = current.name.capitalize()
        holder.doctorAddress.text = current.address

        holder.container.setOnClickListener {
            listener.onItemClick(current)
        }
    }

    internal fun setDoctors(doctors: List<Doctor>) {
        this.doctors = doctors
        notifyDataSetChanged()
    }

    internal fun sortDoctors(sortMethod: Int) {
        val result = doctors.sortedBy {
            when (sortMethod) {
                NAME_SORT -> it.name
                else -> it.name
            }
        }
        setDoctors(result)
    }

    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val container: CardView = itemView.findViewById(R.id.doctor_item_view)
        val doctorName: TextView = itemView.findViewById(R.id.doctor_name)
        val doctorAddress: TextView = itemView.findViewById(R.id.doctor_address)
    }
}