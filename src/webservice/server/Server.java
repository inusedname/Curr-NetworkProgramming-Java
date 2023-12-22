package webservice.server;

import javax.xml.ws.Endpoint;

public class Server {
 
    public static final String WS_URL = "http://localhost:8080/ws/users";
 
    public static void main(String[] args) {
        Endpoint.publish(WS_URL, new UserServiceImpl());
    }
}