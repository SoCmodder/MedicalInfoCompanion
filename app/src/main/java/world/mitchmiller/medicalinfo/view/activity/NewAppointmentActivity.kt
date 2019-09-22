package world.mitchmiller.medicalinfo.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.viewmodel.AppointmentViewModel

class NewAppointmentActivity : AppCompatActivity() {

    private lateinit var viewModel: AppointmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)

        viewModel = ViewModelProviders.of(this).get(AppointmentViewModel::class.java)
    }
}