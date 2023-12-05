package tcp;

import java.io.*;
import java.net.Socket;

public class DataStream {

    /*public static void main(String[] args) throws IOException {
        String address = "10.21.135.64";
        int port = 807;

        Socket socket = new Socket(address, port);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        String value = "B20DCCN535;800";
        dos.writeUTF(value);
        int a = dis.readInt();
        int b = dis.readInt();
        System.out.printf("a=%d, b=%d\n", a, b);
        dos.writeInt(a + b);
        dos.writeInt(a * b);
        socket.close();
    }*/

    public static void main(String[] args) throws IOException {
        String address = "10.21.135.64";
        int port = 807;

        Socket socket = new Socket(address, port);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        String value = "B20DCCN535;911";
        dos.writeUTF(value);
        int a = dis.readInt();
        int b = dis.readInt();
        System.out.printf("a=%d, b=%d\n", a, b);
        dos.writeInt(getGcd(a, b));
        dos.writeInt(getLcm(a, b));
        dos.writeInt(a + b);
        dos.writeInt(a * b);
        socket.close();
    }

    static int getGcd(int a, int b) {
        if (a == 0) return b;
        return getGcd(b % a, a);
    }

    static int getLcm(int a, int b) {
        return a * b / getGcd(a, b);
    }
}