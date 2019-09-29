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
    private val doctorNames: ArrayList<String> = ArrayList()

    private lateinit var viewModel: AppointmentViewModel
    private lateinit var doctorViewModel: DoctorViewModel

    private var apptCal: Calendar = Calendar.getInstance(Locale.getDefault())

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
        setupSaveButton()
        setupDateAndTime()
    }

    private fun setupDoctorSpinner() {
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, doctorNames)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        doctorSpinner.adapter = arrayAdapter
    }

    private fun setupDateAndTime() {
        val dateSetListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                apptCal.set(Calendar.YEAR, year)
                apptCal.set(Calendar.MONTH, month)
                apptCal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                datePickerText.text = DateUtils.fromMillisToTimeString(apptCal.timeInMillis)
            }
        val timeSetListener: TimePickerDialog.OnTimeSetListener = TimePickerDialog.OnTimeSetListener {
                view, hourOfDay, minute ->
            apptCal.set(Calendar.HOUR_OF_DAY, hourOfDay)
            apptCal.set(Calendar.MINUTE, minute)
            timePickerText.text = DateUtils.getDBTimeString(apptCal.timeInMillis)
        }

        timeButton.setOnClickListener {
            val tpd = TimePickerDialog(this, timeSetListener, 12, 0, false)
            tpd.show()
        }

        dateButton.setOnClickListener {
            val dpd = DatePickerDialog(this, dateSetListener, apptCal.get(Calendar.YEAR), apptCal.get(Calendar.MONTH), apptCal.get(Calendar.DAY_OF_MONTH))
            dpd.show()
        }

        datePickerText.text = DateUtils.fromMillisToTimeString(apptCal.timeInMillis)
        timePickerText.text = DateUtils.getDBTimeString(apptCal.timeInMillis)
    }

    private fun setupSaveButton() {
        saveButton.setOnClickListener {
            doctorViewModel.getDoctorByName(doctorSpinner.selectedItem.toString())
                .observe(this, Observer {
                    val date = DateUtils.getDBDateString(apptCal.timeInMillis)
                    val time = DateUtils.getDBTimeString(apptCal.timeInMillis)
                    viewModel.insert(
                        Appointment(
                            0,
                            nameEt.text.toString(),
                            date,
                            time,
                            it.id
                        )
                    ).invokeOnCompletion {
                        finish()
                    }
                })
        }
    }
}