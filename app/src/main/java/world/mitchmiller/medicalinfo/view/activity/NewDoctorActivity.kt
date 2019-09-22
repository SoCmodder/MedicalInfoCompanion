package world.mitchmiller.medicalinfo.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import world.mitchmiller.medicalinfo.R

class NewDoctorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var name: EditText
    private lateinit var address: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_doctor)

        name = findViewById(R.id.name)
        address = findViewById(R.id.doctor_address)
        saveButton = findViewById(R.id.save_button)

        saveButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}