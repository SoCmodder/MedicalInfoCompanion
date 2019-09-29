package world.mitchmiller.medicalinfo.view.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.db.dao.DoctorDao
import world.mitchmiller.medicalinfo.db.model.Appointment
import world.mitchmiller.medicalinfo.db.model.Doctor
import world.mitchmiller.medicalinfo.util.DateUtils
import world.mitchmiller.medicalinfo.viewmodel.AppointmentViewModel
import world.mitchmiller.medicalinfo.viewmodel.DoctorViewModel
import java.time.Year
import java.util.*
import kotlin.collections.ArrayList

class NewAppointmentActivity : AppCompatActivity() {

    private lateinit var nameEt: TextInputEditText
    private lateinit var datePickerText: TextView
    private lateinit var timePickerText: TextView
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button
    private lateinit var doctorSpinner: Spinner
    private lateinit var saveButton: Button
    private val doctorNames : ArrayList<String> = ArrayList()

    private lateinit var viewModel: AppointmentViewModel
    private lateinit var doctorViewModel: DoctorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)

        nameEt = findViewById(R.id.name)
        datePickerText = findViewById(R.id.date_picker_value)
        timePickerText = findViewById(R.id.time_picker_value)
        dateButton = findViewById(R.id.date_picker_button)
        timeButton = findViewById(R.id.time_picker_button)
        doctorSpinner = findViewById(R.id.doctor_spinner)
        saveButton = findViewById(R.id.save_button)

        viewModel = ViewModelProviders.of(this).get(AppointmentViewModel::class.java)
        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel::class.java)

        doctorViewModel.allDoctors.observe(this, Observer {
            for (doc in it) {
                doctorNames.add(doc.name)
            }
            setupDoctorSpinner()
        })

        timeButton.setOnClickListener {
            // Show Time Picker
            val cal: Calendar = Calendar.getInstance(Locale.getDefault())
            val tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timePickerText.text = hourOfDay.toString() + " : " + minute.toString()
            }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false)

            tpd.show()
        }

        dateButton.setOnClickListener {
            // Show Date Picker
            val cal: Calendar = Calendar.getInstance(Locale.getDefault())
            val dpd = DatePickerDialog(this,
               DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    val dateString: String = DateUtils.fromMillisToTimeString(cal.timeInMillis)
                   datePickerText.text = dateString
               },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )

            dpd.show()
        }

        saveButton.setOnClickListener {
            doctorViewModel.getDoctorByName(doctorSpinner.selectedItem.toString()).observe(this, Observer {
                viewModel.insert(Appointment(0, nameEt.text.toString(), datePickerText.text.toString(), timePickerText.text.toString(), it.id))
            })
        }
    }

    private fun setupDoctorSpinner() {
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, doctorNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        doctorSpinner.adapter = arrayAdapter
    }
}