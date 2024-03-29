package server;


import javafx.util.Pair;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class UdpServer {

    public static int port = 2208;

    // User Id và đề bài
    private static final Map<String, String> userIdAndProblem = new HashMap<>();

    static String createProblem() {
        // TODO
        return "";
    }

    static String solveProblem(String problem) {
        // TODO
        return "";
    }

    static boolean validate(String studentAnswer, String problem) {
        return Objects.equals(studentAnswer, solveProblem(problem));
    }

    static void loop(DatagramSocket serverSocket) throws IOException {
        Pair<Integer, String> senderPortAndData = readRequest(serverSocket);
        String request1 = senderPortAndData.getValue();
        System.out.println("Start handshake: " + request1);

        if (!request1.startsWith(";")) {
            String[] studentAnswer = request1.split(";");
            if (studentAnswer.length < 2) return;
            String userId = studentAnswer[0];
            String userData = studentAnswer[1];
            if (!userIdAndProblem.containsKey(userId)) {
                throw new RuntimeException("Không tìm thấy userId: " + userId);
            } else {
                boolean result = validate(userData, userIdAndProblem.get(userId));
                if (result) {
                    System.out.println("ACCEPTED");
                } else {
                    System.out.println("WRONG ANSWER\n"+ "Đề:\n" + userIdAndProblem.get(userId) + "\nĐáp án đúng:\n" + solveProblem(userIdAndProblem.get(userId)) + "\nĐáp án của bạn:\n" + userData);
                }
                System.out.println("=====================================");
                userIdAndProblem.remove(userId);
            }
        } else {
            if (!request1.matches(";B20DCCN\\d{3};\\d{3}")) {
                throw new RuntimeException("Sai định dạng (;B20DCCN535;129): " + request1);
            }
            String userId = UUID.randomUUID().toString().substring(8);
            userIdAndProblem.put(userId, createProblem());
            sendResponse(
                    serverSocket,
                    String.format("%s;%s", userId, userIdAndProblem.get(userId)),
                    InetAddress.getByName("localhost"),
                    senderPortAndData.getKey()
            );
        }
    }

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);

            while (true) {
                loop(serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Pair<Integer, String> readRequest(DatagramSocket socket) throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new Pair<>(packet.getPort(), new String(packet.getData(), 0, buffer.length).trim());
    }

    private static void sendResponse(DatagramSocket socket, String response, InetAddress address, int port) throws IOException {
        byte[] responseData = response.getBytes();
        socket.send(new DatagramPacket(responseData, responseData.length, address, port));
    }
}
