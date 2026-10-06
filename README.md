# 📱 Ứng Dụng Quản Lý Hồ Sơ Sinh Viên (Android - Kotlin)

Dự án Android ứng dụng kiến thức **Lecture 3** (Activity Lifecycle, Intent, Activity Result API, Permissions và Logcat) được viết bằng ngôn ngữ **Kotlin** trên **Android Studio**.

---

## 👤 1. Thông Tin Sinh Viên Mặc Định

* **Họ và tên:** Nguyễn Ngọc Huy
* **MSSV:** 2415053122119
* **Lớp:** 24T1
* **GPA:** 3.2

---

## ✨ 2. Các Tính Năng Chính & Hướng Dẫn Sử Dụng

### 📱 Màn Hình Chính (`MainActivity`)
1. **Xem thông tin hồ sơ:** Hiển thị ảnh đại diện, họ tên, MSSV, lớp và GPA.
2. **1. Chỉnh sửa hồ sơ:** Mở màn hình chỉnh sửa bằng **Explicit Intent**.
3. **2. Đổi ảnh đại diện:** Cho phép chọn ảnh từ thư viện thiết bị bằng Activity Result API (`GetContent`).
4. **3. Gọi điện:** Mở ứng dụng quay số (Dialer) bằng **Implicit Intent** (`ACTION_DIAL`).
5. **4. Xin quyền Camera:** Hiển thị hộp thoại xin quyền Camera hệ thống bằng Activity Result API (`RequestPermission`).

### ✏️ Màn Hình Chỉnh Sửa (`EditProfileActivity`)
1. **Chỉnh sửa thông tin:** Cho phép cập nhật Họ và tên, Lớp, GPA (GPA kiểm tra hợp lệ từ 0.0 đến 4.0).
2. **Nút Lưu:** Đóng gói thông tin mới vào `Intent`, trả về `RESULT_OK` và cập nhật dữ liệu ở `MainActivity`.
3. **Nút Hủy:** Trả về `RESULT_CANCELED`, hủy bỏ thao tác và giữ nguyên dữ liệu ban đầu.

---

## 🛠️ 3. Kiến Thức Lecture 3 Áp Dụng Trong Project

* **Activity Lifecycle:** Theo dõi đầy đủ 7 trạng thái vòng đời (`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onRestart`, `onDestroy`) trong cả 2 Activity. Tất cả được ghi Logcat với tag `TAG_LIFECYCLE`.
* **Explicit Intent:** Dùng chuyển từ `MainActivity` sang `EditProfileActivity`.
* **Intent Extras / Serializable:** Dùng để truyền đối tượng dữ liệu `Student` qua lại giữa các Activity.
* **Activity Result API:** 
  * `ActivityResultContracts.StartActivityForResult()`: Nhận dữ liệu chỉnh sửa trả về từ `EditProfileActivity`.
  * `ActivityResultContracts.GetContent()`: Chọn ảnh từ Gallery.
  * `ActivityResultContracts.RequestPermission()`: Yêu cầu cấp quyền Camera.
  * *Cam kết không sử dụng API cũ đã bị deprecate (`startActivityForResult()` / `onActivityResult()`)*.
* **Implicit Intent:** Mở giao diện gọi điện thoại qua `Intent(Intent.ACTION_DIAL, Uri.parse("tel:..."))`.
* **Manifest Permission:** Khai báo quyền `<uses-permission android:name="android.permission.CAMERA" />` trong `AndroidManifest.xml`.

---

## 📂 4. Cấu Trúc Thư Mục Dự Án

```
app/src/main/
├── java/com/example/ungdungquanlyhososinhvien/
│   ├── Student.kt              # Model lưu thông tin sinh viên (Serializable)
│   ├── MainActivity.kt         # Activity màn hình chính
│   └── EditProfileActivity.kt  # Activity chỉnh sửa hồ sơ
├── res/
│   ├── layout/
│   │   ├── activity_main.xml          # Layout màn hình chính
│   │   └── activity_edit_profile.xml  # Layout màn hình chỉnh sửa
│   ├── drawable/
│   │   └── ic_avatar.xml              # Vector drawable ảnh đại diện mặc định
│   └── values/
│       └── strings.xml                # Chuỗi hiển thị ngôn ngữ Tiếng Việt
└── AndroidManifest.xml                # Khai báo các Activity & quyền ứng dụng
```

---

## 🔍 5. Kiểm Tra Tương Tác & Logcat

1. Mở cửa sổ **Logcat** trong Android Studio.
2. Nhập từ khóa search: `TAG_LIFECYCLE`.
3. Thực hiện chuyển đổi qua lại giữa `MainActivity` và `EditProfileActivity` hoặc ẩn/hiện ứng dụng để quan sát thứ tự gọi các callback Lifecycle của Android.

---

## 🚀 6. Repository GitHub

* **Link GitHub:** [https://github.com/river-com/UngDungQuanLyHoSoSinhVien.git](https://github.com/river-com/UngDungQuanLyHoSoSinhVien.git)
