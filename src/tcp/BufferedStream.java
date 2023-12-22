package tcp;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BufferedStream {
    /*public static void main(String[] args) throws IOException {
        String address = "10.21.135.64";
        int port = 808;

        Socket socket = new Socket(address, port);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String value = "B20DCCN535;600";
        writer.write(value);
        writer.write("\n");
        writer.flush();
        String receive = reader.readLine();
        List<String> filtered = Arrays.stream(receive.split(", ")).filter(x -> x.endsWith(".edu")).toList();
        filtered.forEach(System.out::println);
        String result = String.join(", ", filtered);
        System.out.println(result);
        writer.write(result);
        writer.write("\n");
        writer.flush();
        socket.close();
    }*/

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2208);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        writer.write("B15DCCN999;721");
        writer.flush();
        String s = reader.readLine();
        Map<Character, Integer> freq = new HashMap<>();
        for (Character c: s.toCharArray()) {
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) + 1);
            } else {
                freq.put(c, 1);
            }
        }
        Map<Character, Integer> filtered = freq.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                ;
        String res ="";
        for(Map.Entry<Character, Integer> entry: filtered.entrySet()) {
            res += String.format("%c:%d,", entry.getKey(), entry.getValue());
        }
        writer.write(res);
        writer.flush();
        socket.close();
    }
}
