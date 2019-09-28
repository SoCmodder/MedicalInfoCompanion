package world.mitchmiller.medicalinfo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel

class ViewDoctorActivity : AppCompatActivity() {

    private var doctorId: Int = 0
    private lateinit var doctor: Doctor
    private lateinit var doctorViewModel: DoctorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_doctor)

        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel::class.java)

        doctorId = intent.getIntExtra(DoctorListActivity.DOCTOR_ID_ARG, 0)
        doctor = doctorViewModel.getDoctorById(doctorId).value!!
    }
}