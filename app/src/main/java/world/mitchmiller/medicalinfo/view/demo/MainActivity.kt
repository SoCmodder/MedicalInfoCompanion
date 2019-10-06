package world.mitchmiller.medicalinfo.view.demo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import butterknife.ButterKnife
import butterknife.OnClick
import team.clevel.documentscanner.ImageCropActivity
import team.clevel.documentscanner.helpers.ScannerConstants
import world.mitchmiller.medicalinfo.R
import world.mitchmiller.medicalinfo.util.Constants
import world.mitchmiller.medicalinfo.util.FileUtils
import world.mitchmiller.medicalinfo.view.activity.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NEW_DOCTOR_REQUEST_CODE = 123
        const val NEW_DOCUMENT_REQUEST_CODE = 124
        const val NEW_APPOINTMENT_REQUEST_CODE = 125
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.add_doctor_button)
    fun onAddDoctorClick() {
        val intent = Intent(this, NewDoctorActivity::class.java)
        startActivityForResult(intent,
            NEW_DOCTOR_REQUEST_CODE
        )
    }

    @OnClick(R.id.add_appointment_button)
    fun onAddAppointmentClick() {
        val intent = Intent(this, NewAppointmentActivity::class.java)
        startActivityForResult(intent,
            NEW_APPOINTMENT_REQUEST_CODE
        )
    }

    @OnClick(R.id.add_document_button)
    fun onAddDocumentClick() {
        val intent = Intent(this, NewDocumentActivity::class.java)
        startActivityForResult(intent,
            NEW_DOCUMENT_REQUEST_CODE
        )
    }

    @OnClick(R.id.view_doctors_button)
    fun onViewDoctorClick() {
        val intent = Intent(this, DoctorListActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.view_appointments_button)
    fun onViewAppointmentClick() {
        val intent = Intent(this, ViewAppointmentActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.view_documents_button)
    fun onViewDocumentClick() {
        val intent = Intent(this, ViewDocumentActivity::class.java)
        startActivity(intent)
    }

    @OnClick(R.id.scan_document_button)
    fun onDocumentScannerClick() {
        startActivityForResult(Intent(this, ImageCropActivity::class.java), Constants.IMAGE_CROP_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.IMAGE_CROP_REQUEST && resultCode == Activity.RESULT_OK) {
            if (ScannerConstants.selectedImageBitmap != null) {
                // save the image
                val imageName = FileUtils.createImageFileName()
                FileUtils.createDirectoryAndSaveBitmapFile(ScannerConstants.selectedImageBitmap, imageName)
            }
        }
    }
}
