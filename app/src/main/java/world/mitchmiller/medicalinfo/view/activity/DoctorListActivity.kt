package world.mitchmiller.medicalinfo.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.view.adapter.DoctorAdapter
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel

class DoctorListActivity : AppCompatActivity(), DoctorAdapter.OnItemClickListener {
    private lateinit var recyclerview: RecyclerView
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var coordinatorLayout: CoordinatorLayout

    companion object {
        const val DOCTOR_ID_ARG = "doctor_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        recyclerview = findViewById(R.id.recyclerview)
        fab = findViewById(R.id.fab)
        coordinatorLayout = findViewById(R.id.coordinator_layout)
        recyclerview.layoutManager = LinearLayoutManager(this)
        doctorAdapter = DoctorAdapter(this, this)
        recyclerview.adapter = doctorAdapter

        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel::class.java)
        doctorViewModel.allDoctors.observe(this, Observer {
            docs -> doctorAdapter.setDoctors(docs)
        })

        fab.setOnClickListener {
            val newDocIntent = Intent(this, NewDoctorActivity::class.java)
            startActivityForResult(newDocIntent, 0)
        }
    }

    private fun setupCoordinatorLayout() {

    }

    override fun onItemClick(doctor: Doctor) {
        val viewDoctorIntent = Intent(this, ViewDoctorActivity::class.java)
        viewDoctorIntent.putExtra(DOCTOR_ID_ARG, doctor.id)
        startActivity(viewDoctorIntent)
    }
}