/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author QuangNV
 */
public class Client {
    
    public static void main(String[] args) {
        User user = getQuestion();
        System.out.printf("%s\n%s\n%s", user.getId(), user.getUsername(), user.getBirthday());
    }

    private static User getQuestion() {
        src.UserServiceImplService service = new src.UserServiceImplService();
        src.UserService port = service.getUserServiceImplPort();
        return port.getQuestion();
    }
    
}
