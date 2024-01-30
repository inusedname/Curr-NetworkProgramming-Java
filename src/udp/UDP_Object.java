package udp;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Object {
    private static String address = "localhost";
    private static int port = 2208;

    private static void send(DatagramSocket socket, Object request) throws IOException {
        InetAddress address = InetAddress.getByName(UDP_Object.address);
        byte[] buffer = toBytes(request);
        socket.send(new java.net.DatagramPacket(buffer, buffer.length, address, port));
    }

    private static Object receive(DatagramSocket socket) throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[4096];
        java.net.DatagramPacket packet = new java.net.DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return fromBytes(buffer);
    }

    private static byte[] toBytes(Object object) throws IOException {
        ByteArrayOutputStream bytesOs = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bytesOs);
        oos.writeObject(object);
        return bytesOs.toByteArray();
    }

    private static Object fromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bytesIs = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bytesIs);
        return (Object) ois.readObject();
    }
}
