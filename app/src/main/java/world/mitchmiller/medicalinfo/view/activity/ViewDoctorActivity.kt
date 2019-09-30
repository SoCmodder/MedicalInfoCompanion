package world.mitchmiller.medicalinfo.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel

class ViewDoctorActivity : AppCompatActivity() {

    private var doctorId: Int = 0
    private lateinit var doctor: Doctor
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var scanDocumentButton: Button
    private lateinit var doctorName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_doctor)

        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel::class.java)

        scanDocumentButton = findViewById(R.id.scan_document_button)
        doctorName = findViewById(R.id.name)

        if (intent != null) {
            doctorId = intent!!.getIntExtra(DoctorListActivity.DOCTOR_ID_ARG, 0)
            doctorViewModel.getDoctorById(doctorId).observe(this, Observer {
                doctor = it
                doctorName.text = it.name
            })
        }

        scanDocumentButton.setOnClickListener {
            val i = Intent(this, ScanDocumentActivity::class.java)
            startActivity(i)
        }
    }
}