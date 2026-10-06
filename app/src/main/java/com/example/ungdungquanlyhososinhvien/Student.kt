package com.example.ungdungquanlyhososinhvien

import java.io.Serializable

/**
 * Model class lưu trữ thông tin sinh viên.
 * Implement Serializable để có thể truyền dữ liệu giữa các Activity thông qua Intent/Bundle.
 */
data class Student(
    var name: String,
    var mssv: String,
    var className: String,
    var gpa: Double
) : Serializable
