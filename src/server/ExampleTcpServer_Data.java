package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ExampleTcpServer_Data {

    public static int port = 2208;
    static BufferedReader reader;
    static BufferedWriter writer;
    static DataInputStream dis;
    static DataOutputStream dos;
    static InputStream is;
    static OutputStream os;

    static void setupStreams(Socket socket) throws IOException {
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    static String createProblem() {
        Random random = new Random();
        return random.nextInt(1000) + " " + // a
                random.nextInt(1000);
    }

    static String solveProblem(String rawData) {
        String[] data = rawData.split(" ");
        int a = Integer.parseInt(data[0]);
        int b = Integer.parseInt(data[1]);
        return String.format("%d %d %d %d", getGcd(a, b), getLcm(a, b), a + b, a * b);
    }

    static void sendProblem(String problem) throws IOException {
        String[] numbers = problem.split("\\s");
        dos.writeInt(Integer.parseInt(numbers[0]));
        dos.writeInt(Integer.parseInt(numbers[1]));
    }

    static boolean validate(String problem) {
        String answer = solveProblem(problem);
        try {
            int gcd = dis.readInt();
            int lcm = dis.readInt();
            int sum = dis.readInt();
            int product = dis.readInt();
            String userAnswer = String.format("%d %d %d %d", gcd, lcm, sum, product);
            System.out.println("Đáp án của bạn: " + userAnswer);
            return answer.equals(userAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    static String readHandshake() throws IOException {
        return dis.readUTF();
    }

    static int getGcd(int a, int b) {
        if (a == 0) return b;
        return getGcd(b % a, a);
    }

    static int getLcm(int a, int b) {
        return a * b / getGcd(a, b);
    }

    static void loop() throws IOException {
        String request1 = readHandshake();
        System.out.println("Start handshake: " + request1);

        String problem = createProblem();
        if (!request1.matches("B20DCCN\\d{3};\\d{3}")) {
            throw new RuntimeException("Sai định dạng (;B20DCCN535;129): " + request1);
        }
        sendProblem(problem);

        boolean result = validate(problem);
        if (result) {
            System.out.println("ACCEPTED");
        } else {
            System.out.println("WRONG ANSWER\n"+ "Đề:\n" + problem + "\nĐáp án đúng:\n" + solveProblem(problem));
        }
        System.out.println("=====================================");
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            setupStreams(socket);
            loop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
