# Dạng bài tập WebService
> Link tham khảo: [ví dụ](https://gpcoder.com/5615-java-web-services-jax-ws-soap/#Tao_Web_Service_va_tao_Client_truy_cap_Web_service_voi_JAX-WS_API)

## Đề
- sử dụng hàm User getQuestion() và submitAnswer(User)
- hãy sửa lại user trên sao cho:
- tên in hoa chữ đầu tiên, các chữ còn lại nếu là alphabet thì đưa về viết thường
- ngày sinh ở dạng dd/MM/yyyy, hãy viết lại dưới dạng yyyy/dd/MM
- CHÚ Ý KHÔNG ĐƯỢC SỬA ID

## Chạy code
**B1: Chạy Server**
- ![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/aaed4e96-e45a-4d9c-a0dd-7328a5548fad)
- Mở link: [http://localhost:8080/ws/users?wsdl](http://localhost:8080/ws/users?wsdl)
- Kết quả nhận được:
![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/1e5d0d87-f102-4d07-ba8a-4a027b83ae2e)

**B2: Sinh class các thứ**
- Server chỉ public schema qua link wsdl bên trên, chúng ta cần sinh các class thì mới có thể làm bài tập
* Để tạo ra thư mục src/generated, chúng ta cần thực hiện các bước sau
* 1: Mở Intellij lên, xoá folder **generated**:

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/f527df9b-fbbc-4008-91e1-7e19a9ba76b1)

* 2: Tải [JDK 1.8](https://builds.openlogic.com/downloadJDK/openlogic-openjdk/8u392-b08/openlogic-openjdk-8u392-b08-windows-x64.zip). Extract Here.

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/60c5c4ef-e1f9-4d5e-8f73-7ebac1b35b23)

* 3: Mở PowerShell và chạy:
```sh
.\Downloads\openlogic-openjdk-8u392-b08-windows-64\bin\wsimport.exe -s . http://localhost:8080/ws/users?wsdl
```
![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/78ed78bb-e417-4b54-9a6e-97ddcc5f0372)

* 4: Nó sẽ tạo ra thư mục trong C:\Users\Admin\webservice\server. Hãy mở thư mục đó lên.

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/cce90dab-7ff8-43ce-b7ea-6c82df64bc09)

* 5: Quay lại intellij, tạo package mới:
![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/d563d51f-154c-49a4-8906-a25c99496b0c)
> Gõ **generated**
* 6: Copy toàn bộ đống file trên kia vào bên trong này

> Cần biết là lúc làm bài, sẽ không có package server chứa các class cho chúng ta dùng. Nó ở trên máy giáo viên chứ chúng ta ngoài cái link ra còn đúng cái nịt. Vậy nên mới phải dùng chạy cái bên trên để sinh code

**B3: Chạy Client**

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/c4ba38b4-d2ef-41cc-aeae-7a28afcc8fb3)

