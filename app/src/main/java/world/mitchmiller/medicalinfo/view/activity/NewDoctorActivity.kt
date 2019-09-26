package world.mitchmiller.medicalinfo.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel

class NewDoctorActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var name: TextInputEditText
    private lateinit var address: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var saveButton: Button

    private lateinit var docViewModel: DoctorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_doctor)

        docViewModel = ViewModelProviders.of(this).get(DoctorViewModel::class.java)

        name = findViewById(R.id.name)
        address = findViewById(R.id.doctor_address)
        phone = findViewById(R.id.phone)
        saveButton = findViewById(R.id.save_button)

        saveButton.setOnClickListener(this)

        docViewModel.allDoctors.observe(this, Observer {
            doctors ->

        })
    }

    override fun onClick(v: View?) {
        val doctor = Doctor(0, name.text.toString(), address.text.toString(), phone.text.toString())
        docViewModel.insert(doctor).invokeOnCompletion {
            finish()
        }
    }
}