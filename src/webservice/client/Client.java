package webservice.client;

import generated.User;
import generated.UserService;
import generated.UserServiceImplService;

public class Client {

    /**
     * Để tạo ra thư mục src/generated, chúng ta cần thực hiện các bước sau:
     * B1: Tải một bản JDK 1.8 về máy
     * B2: Chạy terminal:
     *      C:\Users\Admin\.jdks\corretto-1.8.0_372\bin\wsimport.exe -s . http://localhost:8080/ws/users?wsdl
     * B3: Nó sẽ tạo ra thư mục cùng cấp với src: th1/webservice/server
     * B4: Copy tất cả các file bên trong và tạo một package generated mới trong src
     */
    public static void main(String[] args) {
        UserServiceImplService wrapper = new UserServiceImplService();
        UserService service = wrapper.getUserServiceImplPort();

        User user = service.getQuestion();
        System.out.printf("%s\n%s\n%s", user.getId(), user.getUsername(), user.getBirthday());
    }
}