package world.mitchmiller.medicalinfo.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.view.adapter.DoctorAdapter
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel

class DoctorListActivity : AppCompatActivity(), DoctorAdapter.OnItemClickListener {
    private lateinit var recyclerview: RecyclerView
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var doctorAdapter: DoctorAdapter

    companion object {
        const val DOCTOR_ID_ARG = "doctor_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        recyclerview = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        doctorAdapter = DoctorAdapter(this, this)
        recyclerview.adapter = doctorAdapter

        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel::class.java)
        doctorViewModel.allDoctors.observe(this, Observer {
            docs -> doctorAdapter.setDoctors(docs)
        })
    }

    override fun onItemClick(doctor: Doctor) {
        val viewDoctorIntent = Intent(this, ViewDoctorActivity::class.java)
        val args = Bundle()
        args.putInt(DOCTOR_ID_ARG, doctor.id)
        startActivity(viewDoctorIntent, args)
    }
}