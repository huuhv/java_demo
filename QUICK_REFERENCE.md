# 📋 QUICK REFERENCE - User CRUD API

## 🎯 Các File Quan Trọng

| File | Mô tả |
|------|-------|
| `User.java` | Entity - Đại diện bảng users |
| `UserRepository.java` | Repository - Truy vấn database |
| `UserService.java` | Service - Business logic |
| `UserController.java` | Controller - REST API endpoints |
| `application.properties` | Cấu hình ứng dụng |

---

## 🔗 API Endpoints Cheat Sheet

```
BASE: http://localhost:8080/api/users
```

### CREATE
```http
POST /api/users
Content-Type: application/json

{
  "name": "Tên",
  "email": "email@example.com",
  "phone": "0123456789",
  "address": "Địa chỉ"
}
```

### READ ALL
```http
GET /api/users
```

### READ BY ID
```http
GET /api/users/1
```

### READ BY EMAIL
```http
GET /api/users/email/test@example.com
```

### UPDATE
```http
PUT /api/users/1
Content-Type: application/json

{
  "name": "Tên mới",
  "email": "newemail@example.com",
  "phone": "0987654321",
  "address": "Địa chỉ mới"
}
```

### DELETE
```http
DELETE /api/users/1
```

---

## ⚡ Quick Commands

### Chạy ứng dụng
```bash
.\mvnw.cmd spring-boot:run
```

### Build project
```bash
.\mvnw.cmd clean install
```

### Test API với cURL
```bash
# Create
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\"name\":\"Test\",\"email\":\"test@example.com\"}"

# Read All
curl http://localhost:8080/api/users

# Read One
curl http://localhost:8080/api/users/1

# Update
curl -X PUT http://localhost:8080/api/users/1 -H "Content-Type: application/json" -d "{\"name\":\"Updated\",\"email\":\"updated@example.com\"}"

# Delete
curl -X DELETE http://localhost:8080/api/users/1
```

---

## 🗄️ Database Quick Commands

```sql
-- Tạo database
CREATE DATABASE demo_java;

-- Xem users
USE demo_java;
SELECT * FROM users;

-- Insert mẫu
INSERT INTO users (name, email, phone, address) 
VALUES ('Test', 'test@example.com', '0123456789', 'Ha Noi');
```

---

## 📊 HTTP Status Codes

| Code | Meaning | Khi nào? |
|------|---------|----------|
| 200 | OK | Request thành công |
| 201 | Created | Tạo mới thành công |
| 204 | No Content | Xóa thành công / Không có data |
| 404 | Not Found | Không tìm thấy resource |
| 500 | Server Error | Lỗi server |

---

## 🔧 Troubleshooting

| Lỗi | Giải pháp |
|-----|-----------|
| Port 8080 đã được sử dụng | Đổi port trong `application.properties` |
| Cannot connect to database | Kiểm tra MySQL đang chạy |
| No compiler provided | Cài JDK, không phải JRE |
| Cannot resolve symbol | Reload Maven project |

---

## 📁 Project Structure

```
demo/
├── src/main/java/com/example/demo/
│   ├── DemoApplication.java      # Main class
│   ├── User.java                 # Entity
│   ├── UserRepository.java       # Repository
│   ├── UserService.java          # Service
│   └── UserController.java       # Controller
├── src/main/resources/
│   └── application.properties    # Configuration
├── README_USER_API.md            # API Documentation
├── user-api-tests.http           # Test requests
├── database-setup.sql            # SQL script
└── pom.xml                       # Maven config
```

---

## ✅ Checklist Trước Khi Chạy

- [ ] Đã cài JDK (Java 21+)
- [ ] MySQL đang chạy
- [ ] Đã tạo database `demo_java`
- [ ] Cấu hình đúng username/password trong `application.properties`
- [ ] Port 8080 available

---

## 💡 Tips

1. **Test nhanh**: Dùng file `user-api-tests.http` trong IntelliJ
2. **Debug**: Set `spring.jpa.show-sql=true` để xem SQL queries
3. **Format SQL**: `spring.jpa.properties.hibernate.format_sql=true`
4. **Hot reload**: Thêm Spring DevTools dependency

---

**📞 Cần Trợ Giúp?**
- Xem file `README_USER_API.md` để biết chi tiết
- Xem file `TOM_TAT_CRUD_USER.md` để biết tổng quan

**🎉 Happy Coding!**

