package world.mitchmiller.medicalinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import world.mitchmiller.medicalinfo.viewmodel.AppointmentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AppointmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(AppointmentViewModel::class.java)
        viewModel.allMedicalItems.observe(this, Observer { medItems ->

        })
    }
}
