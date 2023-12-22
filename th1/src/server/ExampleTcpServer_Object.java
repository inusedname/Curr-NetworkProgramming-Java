package server;

import model.Product917;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class ExampleTcpServer_Object {

    public static int port = 2208;
    static BufferedReader reader;
    static BufferedWriter writer;
    static DataInputStream dis;
    static DataOutputStream dos;
    static InputStream is;
    static OutputStream os;
    static ObjectInputStream ois;
    static ObjectOutputStream oos;

    static void setupStreams(Socket socket) throws IOException {
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    static Object createProblem() {
        return new Product917(1, "abcd", "T520 thinkpad lenovo", 9981);
    }

    static Object solveProblem(Object answerString) {
        Product917 product917 = (Product917) answerString;
        product917.setName("lenovo thinkpad T520");
        product917.setQuantity(1899);
        return product917;
    }

    static void sendProblem(Object problemRaw) throws IOException {
        oos.writeObject(problemRaw);
    }

    static boolean validate(Object problem) throws IOException, ClassNotFoundException {
        Product917 productSolution = (Product917) solveProblem(problem);
        Product917 productAnswer = (Product917) ois.readObject();

        return  productAnswer.getId() == productSolution.getId() &&
                Objects.equals(productAnswer.getName(), productSolution.getName()) &&
                productAnswer.getQuantity() == productSolution.getQuantity() &&
                Objects.equals(productAnswer.getCode(), productSolution.getCode());
//                && productSolution == productAnswer;
        // nếu cần "use-strict" thì bỏ comment dòng trên, sẽ so sánh cả tham chiếu xem đây có phải là cùng 1 object không
    }

    static String readHandshake() throws IOException, ClassNotFoundException {
        String handshakeMsg = (String) ois.readObject();
        return handshakeMsg;
    }

    static void loop() throws IOException, ClassNotFoundException {
        String request1 = readHandshake();
        System.out.println("Start handshake: " + request1);

        Object problem = createProblem();
        if (!request1.matches("B20DCCN\\d{3};\\d{3}")) {
            throw new RuntimeException("Sai định dạng (;B20DCCN535;129): " + request1);
        }
        sendProblem(problem);

        boolean result = validate(problem);
        if (result) {
            System.out.println("ACCEPTED");
        } else {
            System.out.println("WRONG ANSWER\n" + "Đề:\n" + problem + "\nĐáp án đúng:\n" + solveProblem(problem));
        }
        System.out.println("=====================================");
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            setupStreams(socket);
            loop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
