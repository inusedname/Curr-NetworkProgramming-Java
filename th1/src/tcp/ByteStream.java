package tcp;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ByteStream {
    /*public static void main(String[] args) throws IOException {
        // Kết nối tới server
        Socket socket = new Socket("10.21.135.64", 806);

        // Tạo luồng vào và ra
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // Gửi chuỗi mã sinh viên và mã câu hỏi
        String studentCode = "B20DCCN535;700";
        outputStream.write(studentCode.getBytes());

        // Đọc dữ liệu từ server
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        String dataFromServer = new String(buffer, 0, bytesRead);

        // Tách dữ liệu thành các số nguyên
        String[] numbers = dataFromServer.split("\\|");
        int sum = 0;
        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }

        // Gửi tổng giá trị lên server
        String result = sum + "\n";
        outputStream.write(result.getBytes());

        // Đóng kết nối
        socket.close();
    }*/

    public static void main(String[] args) throws IOException {
        // Kết nối tới server
        Socket socket = new Socket("10.21.135.64", 807);

        // Tạo luồng vào và ra
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // Gửi chuỗi mã sinh viên và mã câu hỏi
        String studentCode = "B20DCCN535;701";
        outputStream.write(studentCode.getBytes());

        // Đọc dữ liệu từ server
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);
        String dataFromServer = new String(buffer, 0, bytesRead);

        // Tách dữ liệu thành các số nguyên
        String[] numbers = dataFromServer.split(",");
        List<Integer> intNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            intNumbers.add(Integer.parseInt(numbers[i]));
        }
        List<Integer> sorted = new ArrayList<>(intNumbers);
        sorted.sort(Comparator.naturalOrder());

        int hieuNhoNhat = Integer.MAX_VALUE;
        int soThuNhatIndex = -1;
        int soThuHaiIndex = -1;
        for (int i = 0; i < sorted.size() - 1; i++) {
            int hieu = sorted.get(i + 1) - sorted.get(i);
            if (hieu < hieuNhoNhat) {
                hieuNhoNhat = hieu;
                soThuNhatIndex = i;
                soThuHaiIndex = i + 1;
            }
        }

        boolean needToSwap = false;
        for (int i = 0; i < sorted.size(); i++) {
            if (intNumbers.get(i) == sorted.get(soThuNhatIndex)) {
                needToSwap = true;
                break;
            }
        }

        if (needToSwap) {
            int temp = soThuNhatIndex;
            soThuNhatIndex = soThuHaiIndex;
            soThuHaiIndex = temp;
        }

        // Gửi tổng giá trị lên server
        String result = String.format("%d,%d,%d", hieuNhoNhat, soThuNhatIndex, soThuHaiIndex) + "\n";
        outputStream.write(result.getBytes());

        // Đóng kết nối
        socket.close();
    }
}
