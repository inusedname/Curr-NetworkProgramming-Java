package tcp;

import model.Product917;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class ObjectStream {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Socket socket = new Socket("localhost", 2208);

        /* ============ !!! NHỚ KHỞI TẠO OutputStream trước InputStream !!! ======================= */
        final ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        final ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        /* == !!! CHỈ DÙNG MỖI readObject() xuyên suốt trong bài, ko được dùng readUTF... !!! ===== */
        oos.writeObject("B20DCCN535;917");
        Product917 product917 = (Product917) ois.readObject();
        String[] nameWords = product917.getName().split(" ");
        String tmp = nameWords[0];
        nameWords[0] = nameWords[nameWords.length - 1];
        nameWords[nameWords.length - 1] = tmp;
        String newName = String.join(" ", nameWords);
        product917.setName(newName);
        String num = String.valueOf(product917.getQuantity());
        String newNum = new StringBuilder().append(num).reverse().toString();
        product917.setQuantity(Integer.parseInt(newNum));

        oos.writeObject(product917);
        socket.close();
    }
}
