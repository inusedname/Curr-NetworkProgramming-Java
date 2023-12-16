package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    public static int port = 2208;
    static BufferedReader reader;
    static BufferedWriter writer;
    static DataInputStream dis;
    static DataOutputStream dos;
    static InputStream is;
    static OutputStream os;

    static void setupStreams(Socket socket) throws IOException {
        // TODO
    }

    static String createProblem() {
        // TODO
        return "";
    }

    static String solveProblem(String answerString) {
        // TODO
        return "";
    }

    static void sendProblem(String problemRaw) throws IOException {
        // TODO
    }

    static boolean validate(String problem) {
        // TODO
        return false;
    }

    static String readHandshake() throws IOException {
        return "Không quan trọng lắm, thích thì implement";
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
