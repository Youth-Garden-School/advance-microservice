# Domain-Driven Design (DDD) là gì?

DDD (Domain-Driven Design) là một cách tổ chức code xoay quanh **nghiệp vụ** – tức là những gì dự án của bạn cần giải quyết trong thực tế. Thay vì chia code theo kiểu kỹ thuật (controller, service, database), DDD tập trung vào việc hiểu rõ vấn đề doanh nghiệp gặp phải, rồi xây dựng code giống cách doanh nghiệp nghĩ và làm.

Nói đơn giản: Nếu bạn làm phần mềm quản lý người dùng (identity), DDD sẽ giúp code của bạn nói rõ: "Đây là người dùng, đây là cách họ đăng ký, đây là quy tắc kiểm tra quyền hạn" – thay vì chỉ là một đống hàm kỹ thuật lộn xộn.

---

## Ý tưởng chính của DDD

1. **Domain là trung tâm**  
   "Domain" là lĩnh vực nghiệp vụ bạn đang làm, như "quản lý danh tính" (identity) hay "quản lý sự kiện" (event). Mọi thứ trong code đều phục vụ domain này, không phải phục vụ công nghệ.  
   *Ví dụ*: Nếu làm app bán hàng, domain là "đặt hàng", "giỏ hàng", "thanh toán". Code sẽ tập trung vào mấy thứ đó.

2. **Bounded Context – Chia nhỏ để dễ quản lý**  
   Một dự án lớn có nhiều domain (như identity, event). DDD bảo bạn chia nhỏ thành từng phần riêng biệt, gọi là "Bounded Context". Mỗi phần tự lo chuyện của nó, không đụng chạm lung tung.  
   *Ví dụ*: Identity lo đăng nhập, Event lo gửi thông báo – hai thứ không cần biết nhau làm gì.

3. **Entity và Aggregate – Những "nhân vật" trong câu chuyện**
    - **Entity**: Là những thứ có "danh tính" trong nghiệp vụ, như "người dùng" (User) với ID riêng. Nó không chỉ là dữ liệu mà còn có hành vi (như User có thể đổi mật khẩu).
    - **Aggregate**: Là nhóm entity liên quan chặt chẽ, có một "trùm" (Aggregate Root) quản lý. Ví dụ: User là trùm, chứa danh sách Role của nó.

4. **Domain Service – Người giải quyết rắc rối**  
   Khi logic nghiệp vụ phức tạp (như kiểm tra quyền trước khi tạo User), bạn không nhét vào entity mà tạo một Domain Service để xử lý.

---

## DDD hoạt động thế nào trong code?

Thay vì chia thư mục kiểu cũ:
- `controller/` (API), `service/` (logic), `repository/` (database),

DDD chia theo cách nghiệp vụ:
- `domain/` (chứa User, Role, logic chính),
- `application/` (điều phối, DTO),
- `infrastructure/` (kết nối DB, API ngoài),
- `interfaces/` (API cho người dùng gọi).

*Ví dụ*: Bạn muốn thêm user mới:
- `UserController` nhận yêu cầu từ người dùng.
- `UserService` (application) kiểm tra đầu vào, gọi `UserDomainService`.
- `UserDomainService` (domain) xử lý logic nghiệp vụ (kiểm tra quyền, mã hóa mật khẩu).
- `UserRepository` (infrastructure) lưu vào MongoDB.

---

## Tại sao DDD hay?

- **Dễ hiểu**: Code giống cách doanh nghiệp nghĩ. Người mới vào đọc cũng biết ngay "User làm được gì".
- **Dễ chia nhỏ**: Làm microservices thì mỗi domain thành một service, không lằng nhằng.
- **Dễ sửa**: Nghiệp vụ thay đổi, bạn chỉ sửa trong `domain/`, không đụng đến phần kết nối DB hay API.

**Nhưng mà**:
- **Khó lúc đầu**: Phải nghĩ nhiều về nghiệp vụ, không phải cứ code đại là xong.
- **Không hợp dự án nhỏ**: Nếu chỉ làm app CRUD đơn giản, DDD hơi "nặng đô".

---

## Ví dụ đời thường

Hãy tưởng tượng bạn mở quán cà phê:
- **Layered Architecture**: Bạn chia công việc kiểu "ai pha chế", "ai giao hàng", "ai ghi sổ". Nhưng khi khách đông, mọi thứ rối tung vì không ai biết rõ "cà phê này làm sao".
- **DDD**: Bạn chia theo kiểu "quy trình bán cà phê" (domain). Có người lo "nhận order", người lo "pha cà phê", người lo "giao hàng". Mỗi người rõ việc của mình, phối hợp ngon lành.

DDD giống như lập kế hoạch cho quán cà phê vậy – tập trung vào cách bán cà phê, chứ không phải chỉ lo cái máy pha chế.

---

## Kết luận

DDD là cách làm code "thông minh", dành cho những dự án cần hiểu rõ nghiệp vụ và dễ mở rộng (như microservices). Nó hơi khó ban đầu, nhưng khi quen rồi, bạn sẽ thấy code gọn gàng, dễ sửa, và đúng với cái doanh nghiệp muốn. Nếu làm identity với event, DDD sẽ giúp bạn tách hai thứ đó rõ ràng, mỗi thứ tự lo chuyện của mình!