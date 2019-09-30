package world.mitchmiller.medicalinfo.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_doctor_list.*
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.view.adapter.DoctorAdapter
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel

class DoctorListActivity : AppCompatActivity(), DoctorAdapter.OnItemClickListener {
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var doctorAdapter: DoctorAdapter

    companion object {
        const val DOCTOR_ID_ARG = "doctor_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        setupToolbar()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_doctor_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
            R.id.new_appointment -> newAppointment()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(doctor: Doctor) {
        val viewDoctorIntent = Intent(this, ViewDoctorActivity::class.java)
        viewDoctorIntent.putExtra(DOCTOR_ID_ARG, doctor.id)
        startActivity(viewDoctorIntent)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = "Doctor List"
        actionBar.elevation = 4.0F

        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    private fun newAppointment() {

    }
}