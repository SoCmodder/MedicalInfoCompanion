package world.mitchmiller.medicalinfo.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel

class ViewDoctorActivity : AppCompatActivity() {

    private var doctorId: Int = 0
    private lateinit var doctor: Doctor
    private lateinit var doctorViewModel: DoctorViewModel
    private lateinit var scanDocumentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_doctor)

        scanDocumentButton = findViewById(R.id.scan_document_button)
        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel::class.java)

        doctorId = intent.getIntExtra(DoctorListActivity.DOCTOR_ID_ARG, 0)
//        doctor = doctorViewModel.getDoctorById(doctorId).value!!

        scanDocumentButton.setOnClickListener {
            val i = Intent(this, ScanDocumentActivity::class.java)
            startActivity(i)
        }
    }
}