package world.mitchmiller.medicalinfo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel

class DoctorListActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView

    private lateinit var doctorViewModel: DoctorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        recyclerview = findViewById(R.id.recyclerview)

        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel::class.java)
    }
}