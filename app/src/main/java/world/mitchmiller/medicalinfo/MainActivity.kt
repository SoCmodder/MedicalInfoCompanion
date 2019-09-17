package world.mitchmiller.medicalinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import world.mitchmiller.medicalinfo.viewmodel.MedicalItemViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MedicalItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MedicalItemViewModel::class.java)
        viewModel.allMedicalItems.observe(this, Observer { medItems ->

        })
    }
}
