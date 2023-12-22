package udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP {

    private static String address = "localhost";
    private static int port = 2208;

    private static void send(DatagramSocket socket, String request) throws IOException {
        InetAddress address = InetAddress.getByName(UDP.address);
        byte[] buffer = request.getBytes();
        socket.send(new java.net.DatagramPacket(buffer, buffer.length, address, port));
    }

    private static String receive(DatagramSocket socket) throws IOException {
        byte[] buffer = new byte[1024];
        java.net.DatagramPacket packet = new java.net.DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String(packet.getData()).trim();
    }

    // Viết hoa chữ cái đầu tiên tất cả các từ trong câu
    public static void main(String[] args) throws IOException {
        String request = ";B20DCCN535;932";
        DatagramSocket socket = new DatagramSocket();

        send(socket, request);

        String resp = receive(socket);
        String requestId = resp.split(";")[0];
        String data = resp.split(";")[1];

        String[] words = data.split("\\s");
        StringBuilder processedData = new StringBuilder();

        for (String word : words) {
            processedData
                    .append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        send(socket, requestId + ";" + processedData.toString().trim());
        socket.close();
    }

    // Tìm số lớn nhất và nhỏ nhất trong mảng
    /*public static void main(String[] args) throws IOException {
        String request = ";B20DCCN535;931";
        DatagramSocket socket = new DatagramSocket();

        send(socket, request);
        String[] resp = receive(socket).split(";");
        Integer[] arr = new Integer[1024];
        for (int i = 1; i < resp.length; i++) {
            arr[i - 1] = Integer.parseInt(resp[i]);
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        String data = resp[0] + ";" + max + ";" + min;
        send(socket, resp[0] + ";" + data);
        socket.close();
    }*/

    // Mã hoá caesar
    /*public static void main(String[] args) throws IOException {
        String request = ";B20DCCN535;934";
        DatagramSocket socket = new DatagramSocket();

        send(socket, request);
        String resp = receive(socket);
        String requestId = resp.split(";")[0];
        String strEncode = resp.split(";")[1];
        String s = resp.split(";")[2];

        // caesar decrypting, with s is offset int
        int offset = Integer.parseInt(s);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strEncode.length(); i++) {
            char c = strEncode.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append((char) ('A' + (c - 'A' - offset) % 26));
            } else {
                result.append((char) ('a' + (c - 'a' - offset) % 26));
            }
        }
        send(socket, requestId + ";" + result.toString());
        socket.close();
    }*/

    // Loại bỏ chữ cái xuất hiện trong chuỗi sau
    /*public static void main(String[] args) throws IOException {
        String request = ";B20DCCN535;936";
        DatagramSocket socket = new DatagramSocket();

        send(socket, request);
        String resp = receive(socket);
        String requestId = resp.split(";")[0];
        String str1 = resp.split(";")[1];
        String str2 = resp.split(";")[2];
        for (String c : str2.split("")) {
            str1 = str1.replace(c, "");
        }
        send(socket, requestId + ";" + str1);
        socket.close();
    }*/

    // phải dùng class
    /*public static class Product937 implements Serializable {
        private static final long serialVersionUID = 937L;
        public String id;
        public String code;
        public String name;
        public int quantity;

        public Product937(String id, String code, String name, int quantity) {
            this.id = id;
            this.code = code;
            this.name = name;
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) throws IOException {
        String request = ";B20DCCN535;937";
        DatagramSocket socket = new DatagramSocket();

        send(socket, request);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        socket.receive(packet);
        ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        UDP.Product937 product = null;
        try {
            product = (UDP.Product937) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert product != null;
        String quantity = String.valueOf(product.quantity);
        String reversed = new StringBuilder(quantity).reverse().toString();
        product.quantity = Integer.parseInt(reversed);
        String[] names = Arrays.stream(product.name.split(" ")).toArray(String[]::new);
        String tmp = names[0];
        names[0] = names[names.length - 1];
        names[names.length - 1] = tmp;
        product.name = String.join(" ", names);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(product);
//        oos.flush();
//        byte[] buffer = baos.toByteArray();
//        socket.send(new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort()));
        socket.close();
    }*/
}
