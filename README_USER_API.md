# User CRUD API Documentation

## Tổng quan
Dự án Spring Boot này cung cấp các API RESTful để quản lý User (người dùng) với đầy đủ các thao tác CRUD (Create, Read, Update, Delete).

## Cấu trúc dự án

### 1. **User.java** - Entity
Đại diện cho bảng `users` trong database với các thuộc tính:
- `id` (Long): ID tự động tăng
- `name` (String): Tên người dùng (bắt buộc)
- `email` (String): Email (bắt buộc, duy nhất)
- `phone` (String): Số điện thoại
- `address` (String): Địa chỉ

### 2. **UserRepository.java** - Repository
Interface kế thừa JpaRepository để thao tác với database:
- Kế thừa các phương thức cơ bản: `save()`, `findAll()`, `findById()`, `delete()`
- Phương thức tùy chỉnh: `findByEmail(String email)`

### 3. **UserService.java** - Service Layer
Xử lý business logic:
- `createUser(User user)`: Tạo user mới
- `getAllUsers()`: Lấy danh sách tất cả user
- `getUserById(Long id)`: Lấy user theo ID
- `getUserByEmail(String email)`: Lấy user theo email
- `updateUser(Long id, User userDetails)`: Cập nhật thông tin user
- `deleteUser(Long id)`: Xóa user

### 4. **UserController.java** - REST Controller
Cung cấp các API endpoints:

## API Endpoints

### 1. Tạo User mới (CREATE)
**Endpoint:** `POST /api/users`

**Request Body:**
```json
{
  "name": "Nguyễn Văn A",
  "email": "nguyenvana@example.com",
  "phone": "0123456789",
  "address": "Hà Nội, Việt Nam"
}
```

**Response:** 
- Status: `201 CREATED`
- Body: User object đã được tạo

**Ví dụ cURL:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Nguyễn Văn A","email":"nguyenvana@example.com","phone":"0123456789","address":"Hà Nội, Việt Nam"}'
```

---

### 2. Lấy danh sách tất cả User (READ ALL)
**Endpoint:** `GET /api/users`

**Response:**
- Status: `200 OK` (có dữ liệu) hoặc `204 NO CONTENT` (không có dữ liệu)
- Body: Mảng các User objects

**Ví dụ cURL:**
```bash
curl -X GET http://localhost:8080/api/users
```

---

### 3. Lấy User theo ID (READ BY ID)
**Endpoint:** `GET /api/users/{id}`

**Path Parameter:**
- `id`: ID của user cần tìm

**Response:**
- Status: `200 OK` (tìm thấy) hoặc `404 NOT FOUND` (không tìm thấy)
- Body: User object

**Ví dụ cURL:**
```bash
curl -X GET http://localhost:8080/api/users/1
```

---

### 4. Lấy User theo Email (READ BY EMAIL)
**Endpoint:** `GET /api/users/email/{email}`

**Path Parameter:**
- `email`: Email của user cần tìm

**Response:**
- Status: `200 OK` (tìm thấy) hoặc `404 NOT FOUND` (không tìm thấy)
- Body: User object

**Ví dụ cURL:**
```bash
curl -X GET http://localhost:8080/api/users/email/nguyenvana@example.com
```

---

### 5. Cập nhật User (UPDATE)
**Endpoint:** `PUT /api/users/{id}`

**Path Parameter:**
- `id`: ID của user cần cập nhật

**Request Body:**
```json
{
  "name": "Nguyễn Văn A - Updated",
  "email": "nguyenvana_updated@example.com",
  "phone": "0987654321",
  "address": "TP. Hồ Chí Minh, Việt Nam"
}
```

**Response:**
- Status: `200 OK` (cập nhật thành công) hoặc `404 NOT FOUND` (không tìm thấy)
- Body: User object đã được cập nhật

**Ví dụ cURL:**
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Nguyễn Văn A - Updated","email":"nguyenvana_updated@example.com","phone":"0987654321","address":"TP. Hồ Chí Minh, Việt Nam"}'
```

---

### 6. Xóa User (DELETE)
**Endpoint:** `DELETE /api/users/{id}`

**Path Parameter:**
- `id`: ID của user cần xóa

**Response:**
- Status: `204 NO CONTENT` (xóa thành công) hoặc `404 NOT FOUND` (không tìm thấy)

**Ví dụ cURL:**
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

---

## Cài đặt và Chạy ứng dụng

### Yêu cầu:
- Java JDK 21 hoặc cao hơn
- MySQL Database
- Maven

### Bước 1: Cấu hình Database
Tạo database MySQL:
```sql
CREATE DATABASE demo_java;
```

Cập nhật file `application.properties` với thông tin database của bạn:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demo_java
spring.datasource.username=root
spring.datasource.password=123456
```

### Bước 2: Build và Run
```bash
# Build project
mvnw clean install

# Run application
mvnw spring-boot:run
```

Hoặc sử dụng IDE (IntelliJ IDEA, Eclipse) để chạy class `DemoApplication.java`

### Bước 3: Test API
Ứng dụng sẽ chạy tại `http://localhost:8080`

Sử dụng Postman, cURL, hoặc bất kỳ REST client nào để test các API endpoints.

---

## Xử lý lỗi

Các API trả về các HTTP status codes sau:
- `200 OK`: Request thành công
- `201 CREATED`: Tạo resource thành công
- `204 NO CONTENT`: Xóa thành công hoặc không có dữ liệu
- `404 NOT FOUND`: Không tìm thấy resource
- `500 INTERNAL SERVER ERROR`: Lỗi server

---

## Ví dụ sử dụng với Postman

### 1. Tạo User mới:
- Method: POST
- URL: http://localhost:8080/api/users
- Headers: Content-Type: application/json
- Body (raw JSON):
```json
{
  "name": "Trần Thị B",
  "email": "tranthib@example.com",
  "phone": "0912345678",
  "address": "Đà Nẵng, Việt Nam"
}
```

### 2. Lấy tất cả Users:
- Method: GET
- URL: http://localhost:8080/api/users

### 3. Lấy User theo ID:
- Method: GET
- URL: http://localhost:8080/api/users/1

### 4. Cập nhật User:
- Method: PUT
- URL: http://localhost:8080/api/users/1
- Headers: Content-Type: application/json
- Body (raw JSON):
```json
{
  "name": "Trần Thị B - Updated",
  "email": "tranthib_new@example.com",
  "phone": "0987654321",
  "address": "Cần Thơ, Việt Nam"
}
```

### 5. Xóa User:
- Method: DELETE
- URL: http://localhost:8080/api/users/1

---

## Ghi chú
- Database sẽ tự động tạo bảng `users` khi chạy ứng dụng lần đầu tiên (nhờ cấu hình `spring.jpa.hibernate.ddl-auto=update`)
- Email phải là duy nhất (unique constraint)
- Tất cả các trường name và email đều bắt buộc (not null)

