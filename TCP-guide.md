# Dạng bài tập TCP
1. Mở file TcpServer

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/c401852f-5790-4d74-ac9d-8321dbecdb12)
2. Có năm hàm ở đây đang ở TODO (tức là bạn phải tự code)
```java
    // Khởi tạo các stream, tuỳ vào dạng bài nào thì hãy gắn cái đó. Ví dụ dạng Data thì nhớ gắn dis và dos
    static void setupStreams(Socket socket) throws IOException {
        // TODO
    }

    // Sinh ra đề bài, output chúng ta quy ước ở dạng "1 293 8192" hoặc "janiw aiojw năndwd".
    static String createProblem() {
        // TODO
        return "";
    }

    // Giải đề, param answerString chính là hàm createProblem bên trên
    static String solveProblem(String answerString) {
        // TODO
        return "";
    }

    // Gửi đề bài cho client. Không viết logic phức tạp ở đây, chỉ bao hàm các hàm write...
    static void sendProblem(String problemRaw) throws IOException {
        // TODO
    }

    // Hàm chấm user. Hàm này chạy xong thì sẽ in ra đúng hay sai và tắt server
    static boolean validate(String problem) {
        // TODO
        return false;
    }
```
Sau đây là ví dụ của bài tập tính GCM, LCD, tổng, tích
```java
    static void setupStreams(Socket socket) throws IOException {
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    static String createProblem() {
        Random random = new Random();
        return random.nextInt(1000) + " " + // a
                random.nextInt(1000);
    }

    static String solveProblem(String rawData) {
        String[] data = rawData.split(" ");
        int a = Integer.parseInt(data[0]);
        int b = Integer.parseInt(data[1]);
        return String.format("%d %d %d %d", getGcd(a, b), getLcm(a, b), a + b, a * b);
    }

    static void sendProblem(String problem) throws IOException {
        String[] numbers = problem.split("\\s");
        dos.writeInt(Integer.parseInt(numbers[0]));
        dos.writeInt(Integer.parseInt(numbers[1]));
    }

    static boolean validate(String problem) {
        String answer = solveProblem(problem);
        try {
            int gcd = dis.readInt();
            int lcm = dis.readInt();
            int sum = dis.readInt();
            int product = dis.readInt();
            String userAnswer = String.format("%d %d %d %d", gcd, lcm, sum, product);
            System.out.println("Đáp án của bạn: " + userAnswer);
            return answer.equals(userAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
```
