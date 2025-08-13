# Gr_Ecomerce

# Project Name: Allure Report Generation with Ecommerce Tests

# 📂 Cấu trúc thư mục dự án Automation Test

Dự án này sử dụng **Java**, **Selenium WebDriver**, **TestNG**, và tích hợp **Allure** để tạo báo cáo.

---

## 📁 Cấu trúc thư mục

```text
Mobile/
├── .idea/ # Cấu hình dự án IntelliJ IDEA (tự động tạo)
├── allure-report/ # Thư mục chứa báo cáo HTML đã build từ Allure
├── allure-results/ # Thư mục chứa file kết quả thô để tạo Allure report
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── Models/ # Chứa các lớp đại diện cho dữ liệu domain hoặc data models
│ │ │ ├── Pages/ # Chứa các lớp Page Object theo mô hình POM
│ │ │ ├── resources/ # Tài nguyên của chương trình (có thể là file cấu hình, dữ liệu test)
│ │ │ └── utils/ # Các class tiện ích hỗ trợ chung (như xử lý chuỗi, file, đọc cấu hình)
│
│ ├── test/
│ │ ├── java/
│ │ │ ├── Common/ # Các lớp dùng chung cho test:
│ │ │ │ ├── BaseTest.java # Lớp cha thiết lập WebDriver và teardown
│ │ │ │ ├── TestListener.java # Lắng nghe sự kiện test để chụp ảnh màn hình khi lỗi (ITestListener)
│ │ │ │ └── WaitUtils.java # Lớp chứa hàm chờ tùy chỉnh (explicit wait)
│ │ │ └── Test/ # Chứa các test case được viết theo module hoặc chức năng
│
│ └── resources/
│ └── testNg.xml # File cấu hình cho TestNG (suite test, listener, include/exclude...)
│
├── target/ # Thư mục Maven build output (tự động sinh)
├── .gitignore # File cấu hình bỏ qua các file/thư mục khi đẩy lên Git
├── LICENSE # Thông tin giấy phép mã nguồn (nếu có)
├── pom.xml # File cấu hình Maven (thư viện, plugin, version)
└── README.md # Tài liệu mô tả dự án (có thể là tài liệu này)


---
```

## 🧪 Giải thích một số thành phần chính

| Thành phần            | Mô tả                                                                                                |
|-----------------------|------------------------------------------------------------------------------------------------------|
| **BaseTest.java**     | Thiết lập và đóng trình duyệt WebDriver. Các lớp test kế thừa lớp này.                               |
| **TestListener.java** | Cài đặt ITestListener để lắng nghe test case bị fail, chụp screenshot và gửi về Allure.              |
| **WaitUtils.java**    | Các hàm hỗ trợ chờ rõ ràng (explicit wait) để đồng bộ với DOM.                                       |
| **Pages/**            | Chứa các lớp đại diện cho mỗi trang giao diện người dùng (UI) – tuân theo mô hình Page Object Model. |
| **Models/**           | Các class mô tả dữ liệu test, data transfer object hoặc mẫu cho input/output.                        |
| **testNg.xml**        | Khai báo danh sách test suite, chỉ định class test, group, listener...                               |

---

## 📊 Hướng dẫn sử dụng Allure Report

Sau khi chạy test bằng Maven, bạn có thể chạy lệnh sau để sinh báo cáo Allure:

```bash
allure serve allure-results
```

Sinh báo cáo tĩnh:

```bash
allure generate allure-results --clean -o allure-report
```

Sau đó mở file index.html trong thư mục allure-report để xem báo cáo.

Hoặc generate một file index.html cho file báo cáo gọn nhẹ

```bash
allure generate allure-results --single-file --clean -o allure-report
```
