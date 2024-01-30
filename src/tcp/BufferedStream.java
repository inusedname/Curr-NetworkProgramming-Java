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
        String s = "jwncpoawnv78)(2n wa*(&30982";
        s = s.replaceAll("[^a-zA-Z]", "");
        System.out.println(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.toCharArray()[i];

            // cái này để cho hàm replaceAll bên dưới ko ném excepion
            if (i + 1 == s.length()) break;

            // xoá toàn bộ xuất hiện char c phía sau vị trí i+1
            s = s.substring(0, i + 1) + s.substring(i + 1).replaceAll(String.valueOf(c), "");
        }
        System.out.println(s);
    }
}
