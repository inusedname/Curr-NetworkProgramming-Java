package server;


import javafx.util.Pair;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class ExampleUdpServer {

    public static int port = 2208;
    private static final Map<String, String> userIdAndData = new HashMap<>();

    static String createProblem() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        // Generate a random number of words (between 3 and 8)
        int numWords = random.nextInt(6) + 3;

        for (int i = 0; i < numWords; i++) {
            // Generate a random word length (between 5 and 10 characters)
            int wordLength = random.nextInt(6) + 5;

            // Generate a random word with mixed case and alphanumeric characters
            String randomWord = generateRandomWord(wordLength);

            // Append the word to the string builder
            stringBuilder.append(randomWord);

            // Add a space between words (except for the last word)
            if (i < numWords - 1) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }

    private static String generateRandomWord(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Generate a random character (uppercase letter, lowercase letter, or digit)
            char randomChar = (char) (random.nextInt(57) + 65);

            // Append the character to the string builder
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    static String solveProblem(String rawData) {
        String[] words = rawData.split("\\s");
        StringBuilder processedData = new StringBuilder();

        for (String word : words) {
            processedData.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
        }
        return processedData.toString().trim();
    }

    static boolean validate(String studentAnswer, String rawData) {
        return Objects.equals(studentAnswer, solveProblem(rawData));
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
            if (!userIdAndData.containsKey(userId)) {
                throw new RuntimeException("Không tìm thấy userId: " + userId);
            } else {
                boolean result = validate(userData, userIdAndData.get(userId));
                if (result) {
                    System.out.println("ACCEPTED");
                } else {
                    System.out.println("WRONG ANSWER\n"+ "Đề:\n" + userIdAndData.get(userId) + "\nĐáp án đúng:\n" + solveProblem(userIdAndData.get(userId)) + "\nĐáp án của bạn:\n" + userData);
                }
                System.out.println("=====================================");
                userIdAndData.remove(userId);
            }
        } else {
            if (!request1.matches(";B20DCCN\\d{3};\\d{3}")) {
                throw new RuntimeException("Sai định dạng (;B20DCCN535;129): " + request1);
            }
            String userId = UUID.randomUUID().toString().substring(8);
            userIdAndData.put(userId, createProblem());
            sendResponse(
                    serverSocket,
                    String.format("%s;%s", userId, userIdAndData.get(userId)),
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
