package webservice.client;

import generated.User;
import generated.UserService;
import generated.UserServiceImplService;

import java.util.Arrays;

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
        UserServiceImplService userServiceImplService = new UserServiceImplService();
        UserService userService = userServiceImplService.getUserServiceImplPort();

        User user1 = new User();
        user1.setId(1);
        user1.setUsername("gpcoder.com");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("gpcoder.com");

        System.out.println("Insert User : " + userService.insert(user1));
        System.out.println("Insert User : " + userService.insert(user2));

        System.out.println("Get User : " + userService.get(user1.getId()));

        user1.setUsername("gpcoder edited");
        System.out.println("Update User : " + userService.update(user1));

        System.out.println("Get all Users : " + Arrays.asList(userService.getAll()));

        System.out.println("Delete User : " + userService.delete(user1.getId()));
    }
}