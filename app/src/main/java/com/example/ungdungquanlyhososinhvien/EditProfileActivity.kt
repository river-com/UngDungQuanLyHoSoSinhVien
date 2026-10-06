package com.example.ungdungquanlyhososinhvien

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ungdungquanlyhososinhvien.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private val TAG_LIFECYCLE = "TAG_LIFECYCLE"
    private var currentStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LIFECYCLE, "EditProfileActivity: onCreate")

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Nhận đối tượng Student được truyền từ MainActivity qua Intent
        currentStudent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_STUDENT", Student::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_STUDENT") as? Student
        }

        // Đưa dữ liệu hiện tại lên giao diện
        currentStudent?.let { student ->
            binding.edtName.setText(student.name)
            binding.edtClass.setText(student.className)
            binding.edtGpa.setText(student.gpa.toString())
        }

        // Xử lý sự kiện nút Lưu
        binding.btnSave.setOnClickListener {
            val name = binding.edtName.text.toString().trim()
            val className = binding.edtClass.text.toString().trim()
            val gpaStr = binding.edtGpa.text.toString().trim()

            if (name.isEmpty() || className.isEmpty() || gpaStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gpa = gpaStr.toDoubleOrNull()
            if (gpa == null || gpa < 0.0 || gpa > 4.0) {
                Toast.makeText(this, "GPA phải là số thực từ 0.0 đến 4.0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedStudent = Student(
                name = name,
                mssv = currentStudent?.mssv ?: "2415053122119",
                className = className,
                gpa = gpa
            )

            val resultIntent = Intent().apply {
                putExtra("EXTRA_STUDENT", updatedStudent)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        // Xử lý sự kiện nút Hủy
        binding.btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_LIFECYCLE, "EditProfileActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_LIFECYCLE, "EditProfileActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_LIFECYCLE, "EditProfileActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_LIFECYCLE, "EditProfileActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG_LIFECYCLE, "EditProfileActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LIFECYCLE, "EditProfileActivity: onDestroy")
    }
}
