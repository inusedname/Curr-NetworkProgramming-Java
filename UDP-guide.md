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
