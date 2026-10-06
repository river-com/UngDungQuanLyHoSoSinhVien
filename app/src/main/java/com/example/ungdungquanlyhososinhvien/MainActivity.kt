package com.example.ungdungquanlyhososinhvien

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ungdungquanlyhososinhvien.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG_LIFECYCLE = "TAG_LIFECYCLE"

    // Dữ liệu sinh viên mặc định
    private var student = Student(
        name = "Nguyễn Ngọc Huy",
        mssv = "2415053122119",
        className = "24T1",
        gpa = 3.2
    )

    // 1. Activity Result API: Nhận kết quả từ EditProfileActivity
    private val editProfileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val updatedStudent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getSerializableExtra("EXTRA_STUDENT", Student::class.java)
            } else {
                @Suppress("DEPRECATION")
                result.data?.getSerializableExtra("EXTRA_STUDENT") as? Student
            }

            if (updatedStudent != null) {
                student = updatedStudent
                updateUI()
                Toast.makeText(this, "Đã cập nhật thông tin hồ sơ!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Đã hủy chỉnh sửa", Toast.LENGTH_SHORT).show()
        }
    }

    // 2. Activity Result API: Chọn ảnh từ Gallery (GetContent)
    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            binding.imgAvatar.setImageURI(uri)
            Toast.makeText(this, "Đã cập nhật ảnh đại diện!", Toast.LENGTH_SHORT).show()
        }
    }

    // 3. Activity Result API: Xin quyền Camera (RequestPermission)
    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Quyền Camera đã được cấp!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Quyền Camera bị từ chối!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_LIFECYCLE, "MainActivity: onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiển thị thông tin mặc định
        updateUI()

        // 1. Nút Chỉnh sửa hồ sơ (Explicit Intent)
        binding.btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java).apply {
                putExtra("EXTRA_STUDENT", student)
            }
            editProfileLauncher.launch(intent)
        }

        // 2. Nút Đổi ảnh đại diện (GetContent Contract)
        binding.btnChangeAvatar.setOnClickListener {
            selectImageLauncher.launch("image/*")
        }

        // 3. Nút Gọi điện (Implicit Intent ACTION_DIAL)
        binding.btnCall.setOnClickListener {
            val phoneNumber = "0987654321"
            val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(dialIntent)
        }

        // 4. Nút Xin quyền Camera (RequestPermission Contract)
        binding.btnRequestCamera.setOnClickListener {
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun updateUI() {
        binding.tvName.text = student.name
        binding.tvMssv.text = student.mssv
        binding.tvClass.text = student.className
        binding.tvGpa.text = student.gpa.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_LIFECYCLE, "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_LIFECYCLE, "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_LIFECYCLE, "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_LIFECYCLE, "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG_LIFECYCLE, "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_LIFECYCLE, "MainActivity: onDestroy")
    }
}
