# Dạng bài tập WebService
> Nguồn: [ví dụ](https://gpcoder.com/5615-java-web-services-jax-ws-soap/#Tao_Web_Service_va_tao_Client_truy_cap_Web_service_voi_JAX-WS_API)
## Cách làm dạng này (cta sẽ chỉ code phần client, giống như các bài thực hành TCP UDP)
**B0: Chạy Server**
- Vào class webservice/Server và chạy hàm main
- Mở link (http://localhost:8080/ws/users?wsdl)[http://localhost:8080/ws/users?wsdl]
- Kết quả nhận được là một đống text ở format xml, đen kịt

**B1: Gen các class có được từ server**
- Bước này là bắt buộc trước khi thao tác thêm với bất cứ dữ liệu nào khác. Server chỉ public schema hay định nghĩa class thông qua link wsdl bên trên, và chúng ta cần gen ra class Java
* Để tạo ra thư mục src/generated, chúng ta cần thực hiện các bước sau
* 1: Tải một bản (JDK 1.8)[https://builds.openlogic.com/downloadJDK/openlogic-openjdk/8u392-b08/openlogic-openjdk-8u392-b08-windows-x64.zip]. Giải nén file zip tải về thì sẽ thấy thư mục bin và wsimport trong đấy
* 2: Mở PowerShell và chạy:
*      C:\Users\Downloads\openlogic-openjdk-8u392-b08-windows-x64\bin\wsimport.exe -s . http://localhost:8080/ws/users?wsdl
* 3: Nó sẽ tạo ra thư mục trong C:\Users\Admin: th1/webservice/server
* 4: Tạo một package generated mới trong src, bỏ hết chỗ file kia vào đấy

> Cần biết là lúc làm bài, sẽ không có package server chứa các class cho chúng ta dùng. Nó ở trên máy giáo viên chứ chúng ta ngoài cái link ra còn đúng cái nịt. Vậy nên mới phải dùng chạy cái bên trên để sinh code

**B2: Chạy Client**
- Vào class webservice/Client và chạy hàm main
