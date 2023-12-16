# Dạng bài tập UDP
## Chạy server chấm bài UDP
1. Mở file UdpServer

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/ac4bcc0d-2edf-49f1-9847-ddadc0d20024)

2. Có hai hàm ở đây đang ở TODO (tức là bạn phải tự code)
```java
    static String createProblem() {
        // TODO
        return "";
    }
    static String solveProblem(String problem) {
        // TODO
        return "";
    }
```
Sau đây là ví dụ cho bài viết hoa tất cả chữ cái đầu tiên:
```java
// src/server/ExampleUdpServer.java
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
```
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

# Dạng bài tập WebService
> Link tham khảo: [ví dụ](https://gpcoder.com/5615-java-web-services-jax-ws-soap/#Tao_Web_Service_va_tao_Client_truy_cap_Web_service_voi_JAX-WS_API)
## Chạy code
**B1: Chạy Server**
- ![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/aaed4e96-e45a-4d9c-a0dd-7328a5548fad)
- Mở link: [http://localhost:8080/ws/users?wsdl](http://localhost:8080/ws/users?wsdl)
- Kết quả nhận được:
![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/1e5d0d87-f102-4d07-ba8a-4a027b83ae2e)

**B2: Sinh class các thứ**
- Server chỉ public schema qua link wsdl bên trên, chúng ta cần sinh các class thì mới có thể làm bài tập
* Để tạo ra thư mục src/generated, chúng ta cần thực hiện các bước sau
* 1: Mở Intellij lên, xoá folder **generated**:

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/f527df9b-fbbc-4008-91e1-7e19a9ba76b1)

* 2: Tải [JDK 1.8](https://builds.openlogic.com/downloadJDK/openlogic-openjdk/8u392-b08/openlogic-openjdk-8u392-b08-windows-x64.zip). Extract Here.

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/60c5c4ef-e1f9-4d5e-8f73-7ebac1b35b23)

* 3: Mở PowerShell và chạy:
```sh
.\Downloads\openlogic-openjdk-8u392-b08-windows-64\bin\wsimport.exe -s . http://localhost:8080/ws/users?wsdl
```
![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/78ed78bb-e417-4b54-9a6e-97ddcc5f0372)

* 4: Nó sẽ tạo ra thư mục trong C:\Users\Admin\webservice\server. Hãy mở thư mục đó lên.

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/cce90dab-7ff8-43ce-b7ea-6c82df64bc09)

* 5: Quay lại intellij, tạo package mới:
![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/d563d51f-154c-49a4-8906-a25c99496b0c)
> Gõ **generated**
* 6: Copy toàn bộ đống file trên kia vào bên trong này

> Cần biết là lúc làm bài, sẽ không có package server chứa các class cho chúng ta dùng. Nó ở trên máy giáo viên chứ chúng ta ngoài cái link ra còn đúng cái nịt. Vậy nên mới phải dùng chạy cái bên trên để sinh code

**B3: Chạy Client**

![image](https://github.com/inusedname/Curr-NetworkProgramming-Java/assets/49682088/c4ba38b4-d2ef-41cc-aeae-7a28afcc8fb3)

